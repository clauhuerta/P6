package es.ufv.dis.back.fin.CHRG;

import com.google.gson.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class PDFGenerator {
    public static void generar(String inputJson, String outputPdf) {
        try {
            Reader reader = new FileReader(inputJson);
            Type tipoLista = new com.google.gson.reflect.TypeToken<List<Usuario>>(){}.getType();
            List<Usuario> usuarios = new Gson().fromJson(reader, tipoLista);
            reader.close();

            Document doc = new Document(PageSize.A4);
            PdfWriter.getInstance(doc, new FileOutputStream(outputPdf));
            doc.open();

            for (Usuario u : usuarios) {
                doc.add(new Paragraph("Nombre: " + u.getNombre()));
                doc.add(new Paragraph("Apellidos: " + u.getApellidos()));
                doc.add(new Paragraph("NIF: " + u.getNif()));
                doc.add(new Paragraph("Email: " + u.getEmail()));
                doc.add(new Paragraph("Dirección: " + u.getDireccion().getCalle() + ", " +
                        u.getDireccion().getNumero() + ", " + u.getDireccion().getCodigoPostal() +
                        ", " + u.getDireccion().getPisoLetra() + ", " + u.getDireccion().getCiudad()));
                doc.add(new Paragraph("Método de pago: " + u.getMetodoPago().getNumeroTarjeta() +
                        " (" + u.getMetodoPago().getNombreAsociado() + ")"));
                doc.add(Chunk.NEWLINE);
            }

            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
