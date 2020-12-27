import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Decomposto {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int valorDoTeclado = Integer.parseInt(st.nextToken());
		
		int [] notas = {100, 50, 20, 10, 5, 2, 1};
		int [] quantidades = new int[7];
		int resto = valorDoTeclado;

        for(int i = 0; i < 7; i++) {
			quantidades[i] = resto / notas[i];
			
			resto = resto - (quantidades[i] * notas[i]);
		}

		System.out.println(valorDoTeclado);

		for(int i = 0; i < notas.length; i++) {
			System.out.println(String.format("%d nota(s) de R$ %d,00", quantidades[i], notas[i]));
		}

    }
}
