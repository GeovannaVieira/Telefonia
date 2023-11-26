import java.text.SimpleDateFormat;
import java.util.Date;

public class Recarga {
    private Date data; 
    private float valor; //Em reais

    public Recarga(Date data, float valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de recarga inválido!");
        }

        this.data = data;
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public float getValor() {
        return valor;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(data);
        return "Data: " + formattedDate + ", Valor: R$" + valor;
    }
}
