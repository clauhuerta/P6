package es.ufv.dis.back.fin.CHRG;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioService {
    private final String FILE_PATH = "usuarios.json";
    private final Gson gson = new Gson();

    public List<Usuario> getUsuarios() {
        try {
            Reader reader = new FileReader(FILE_PATH);
            Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();
            List<Usuario> usuarios = gson.fromJson(reader, tipoLista);
            reader.close();
            return usuarios != null ? usuarios : new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Usuario getUsuarioPorId(String id) {
        return getUsuarios().stream()
                .filter(u -> u.getId().toString().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addUsuario(Usuario nuevo) {
        List<Usuario> usuarios = getUsuarios();
        if (nuevo.getId() == null) {
            nuevo.setId(UUID.randomUUID());
        }
        usuarios.add(nuevo);
        guardar(usuarios);
    }

    public void updateUsuario(String id, Usuario actualizado) {
        List<Usuario> usuarios = getUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().toString().equals(id)) {
                usuarios.set(i, actualizado);
                break;
            }
        }
        guardar(usuarios);
    }

    private void guardar(List<Usuario> usuarios) {
        try {
            Writer writer = new FileWriter(FILE_PATH);
            gson.toJson(usuarios, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
