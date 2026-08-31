package rs.ac.bg.fon.njt.kartonPredmetaBackend.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.LiteraturaDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.PredispitnaObavezaDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.PredmetDTO;

import java.io.ByteArrayOutputStream;

@Component
public class KartonPdfGenerator {

    public byte[] generisi(PredmetDTO predmet) {
        Document document = new Document(PageSize.A4, 40, 40, 50, 50);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font naslovFont = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font podnaslovFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font tekstFont = new Font(Font.HELVETICA, 10, Font.NORMAL);

            Paragraph naslov = new Paragraph("Karton predmeta", naslovFont);
            naslov.setAlignment(Element.ALIGN_CENTER);
            naslov.setSpacingAfter(15);
            document.add(naslov);

            dodajRed(document, "Naziv predmeta:", predmet.getNaziv(), podnaslovFont, tekstFont);
            dodajRed(document, "Šifra:", predmet.getSifra(), podnaslovFont, tekstFont);
            dodajRed(document, "Studijski program:", predmet.getStudijskiProgramNaziv(), podnaslovFont, tekstFont);
            if (predmet.getModulNaziv() != null) {
                dodajRed(document, "Modul:", predmet.getModulNaziv(), podnaslovFont, tekstFont);
            }
            dodajRed(document, "Godina studija:", String.valueOf(predmet.getGodinaStudija()), podnaslovFont, tekstFont);
            dodajRed(document, "Semestar:", String.valueOf(predmet.getSemestar()), podnaslovFont, tekstFont);
            dodajRed(document, "ESPB:", String.valueOf(predmet.getEspb()), podnaslovFont, tekstFont);
            dodajRed(document, "Status:", predmet.getStatus().toString(), podnaslovFont, tekstFont);
            dodajRed(document, "Fond časova:", predmet.getFondPredavanja() + "+" + predmet.getFondVezbi(), podnaslovFont, tekstFont);
            dodajRed(document, "Nosilac predmeta:", predmet.getNosilacImePrezime(), podnaslovFont, tekstFont);

            if (predmet.getNastavniciImenaPrezimena() != null && !predmet.getNastavniciImenaPrezimena().isEmpty()) {
                dodajRed(document, "Nastavnici:", String.join(", ", predmet.getNastavniciImenaPrezimena()), podnaslovFont, tekstFont);
            }

            dodajPasus(document, "Cilj predmeta", predmet.getCilj(), podnaslovFont, tekstFont);
            dodajPasus(document, "Ishodi učenja", predmet.getIshodiUcenja(), podnaslovFont, tekstFont);
            dodajPasus(document, "Sadržaj predavanja", predmet.getSadrzajPredavanja(), podnaslovFont, tekstFont);
            dodajPasus(document, "Sadržaj vežbi", predmet.getSadrzajVezbi(), podnaslovFont, tekstFont);
            dodajPasus(document, "Način polaganja ispita", predmet.getNacinPolaganja(), podnaslovFont, tekstFont);

            if (predmet.getPredispitneObaveze() != null && !predmet.getPredispitneObaveze().isEmpty()) {
                Paragraph naslovObaveze = new Paragraph("Predispitne obaveze i raspodela poena", podnaslovFont);
                naslovObaveze.setSpacingBefore(10);
                document.add(naslovObaveze);
                for (PredispitnaObavezaDTO obaveza : predmet.getPredispitneObaveze()) {
                    document.add(new Paragraph("- " + obaveza.getNaziv() + ": " + obaveza.getBrojPoena() + " poena", tekstFont));
                }
                document.add(new Paragraph("- Ispit: " + predmet.getPoeniIspit() + " poena", tekstFont));
            }

            if (predmet.getLiteratura() != null && !predmet.getLiteratura().isEmpty()) {
                Paragraph naslovLiterature = new Paragraph("Literatura", podnaslovFont);
                naslovLiterature.setSpacingBefore(10);
                document.add(naslovLiterature);
                for (LiteraturaDTO lit : predmet.getLiteratura()) {
                    String prefiks = lit.getTip().toString().equals("OBAVEZNA") ? "[Obavezna] " : "[Preporučena] ";
                    document.add(new Paragraph("- " + prefiks + lit.getAutor() + ": " + lit.getNaziv(), tekstFont));
                }
            }

            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Greška prilikom generisanja PDF-a: " + e.getMessage(), e);
        }

        return outputStream.toByteArray();
    }

    private void dodajRed(Document document, String labela, String vrednost, Font labelaFont, Font vrednostFont) throws DocumentException {
        Paragraph p = new Paragraph();
        p.add(new Chunk(labela + " ", labelaFont));
        p.add(new Chunk(vrednost != null ? vrednost : "-", vrednostFont));
        p.setSpacingAfter(5);
        document.add(p);
    }

    private void dodajPasus(Document document, String naslov, String tekst, Font naslovFont, Font tekstFont) throws DocumentException {
        if (tekst == null || tekst.isBlank()) {
            return;
        }
        Paragraph p = new Paragraph(naslov, naslovFont);
        p.setSpacingBefore(10);
        document.add(p);
        document.add(new Paragraph(tekst, tekstFont));
    }
}