package com.feluma.naa.util.utilitario;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Utilitario {

	@SuppressWarnings("unused")
	/**
	 * metodo que retorna a data atual no formato String "dd/MM/yyyy"
	 * 
	 * @return data no formato "dd/MM/yyyy"
	 */
	public static Date dataAtualString() {
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return data;
	}
	
	/**
	 * metodo que retorna a data atual no formato Date
	 * 
	 * @return data no formato "dd/MM/yyyy"
	 */
	public static Calendar dataAtualDate() {
		Calendar dataAtual = Calendar.getInstance();
		dataAtual.setTime(new Date());
		dataAtual.set(Calendar.HOUR_OF_DAY, 0);
		dataAtual.set(Calendar.MINUTE, 0);
		dataAtual.set(Calendar.SECOND, 0);
		dataAtual.set(Calendar.MILLISECOND, 0);
		
		return dataAtual;
	}

	/**
	 * Metodo que formata a data em formato sql para ser usando no banco de
	 * dados.
	 * 
	 * @param date
	 * @return data no formato "yyyy-MM-dd"
	 */
	public static String formateDate(Date date) {
		String formattedDate = "";
		formattedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date);
		return formattedDate;
	}
	
	/**
	 * Metodo que formata um double para moeda String
	 * dados.
	 * 
	 * @param double
	 * @return String no formato "999.999,00"
	 */
	public static String valorMoeda(Double valorTotal) {
		BigDecimal valor = new BigDecimal (valorTotal);  
		NumberFormat nf = NumberFormat.getCurrencyInstance();  
		return nf.format(valor);
	}

	/**
	 * Metodo statico que recebe um numero e o a quantidade de casas decimais
	 * desejadas, formata o numero obedecendo as regras de arredonadamento
	 * 
	 * @param numero
	 * @param casas
	 * @return double arredondado
	 */
	public static double arredondar(double numero, int casas) {
		return (new BigDecimal(numero).setScale(casas, RoundingMode.HALF_EVEN)).doubleValue();
	}

	/**
	 * Metodo que recebe um set e develove um list
	 * 
	 * @param set
	 * @return
	 */
	public static <U> List<U> convertSetToList(Set<U> set) {
		return new ArrayList<U>(set);
	}

	/**
	 * Metodo que gera uma lista de anos sequenciais com base no ano inicio e no
	 * ano fim passando como paramentro
	 * 
	 * @param anoInicio
	 * @param anoFim
	 * @return lista de integer com os anos
	 */
	public static List<Integer> listaAno(Integer anoInicio, Integer anoFim) {
		List<Integer> retorno = new ArrayList<>();
		for (int i = anoInicio; i <= anoFim; i++) {
			retorno.add(i);
		}
		return retorno;
	}

	/**
	 * Metodo que recebe uma classe de Enum e uma Lista de Strings contendo o
	 * nome dos itens do enum que nao deverao aprecer
	 * 
	 * @param A
	 * classe do Enum - lista de enum (enuns a serem excluidos) -
	 * operador (+ para adicionar os itens da lista e - para remover
	 * os itens da lista)
	 * @return retorna um lista de enums apenas com os nomes que nao foram
	 * passados na lista
	 */
	public static <E extends Enum<E>> List<E> filtrarEnum(Class<E> clazz, List<E> lista, String operador) {
		List<E> retorno = new ArrayList<E>();
		if (lista.size() > 0) {
			for (E en : EnumSet.allOf(clazz)) {
				if (!((operador.equals("+") || lista.contains(en)) && !(operador.equals("+") && lista.contains(en)))) {
					retorno.add(en);
				}
			}
		} else {
			for (E en : EnumSet.allOf(clazz)) {
				retorno.add(en);
			}
		}
		return retorno;
	}

	/**
	 * Metodo para remover a mascara de um cpf ou cep
	 * 
	 * @param cpf
	 *            com mascara
	 * @return cpf sem mascara
	 */
	public static String removerMascara(String cpf) {
		return cpf.replace(".", "").replace("-", "").replace("(", "").replace(")", "");
	}

	/**
	 * Metodo para gerar string aleatória
	 * 
	 * @param
	 * @return string aleatória
	 */
	public static String gerarStringAleatoria() {
		int qtdeMaximaCaracteres = 30;
		String[] caracteres = { "a", "1", "b", "2", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B",
				"C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z" };

		StringBuilder senha = new StringBuilder();

		for (int i = 0; i < qtdeMaximaCaracteres; i++) {
			int posicao = (int) (Math.random() * caracteres.length);
			senha.append(caracteres[posicao]);
		}

		return senha.toString();
	}
	
	/**
	 * Metodo para buscar CEP
	 * 
	 * @param cep
	 * @return json do enderço completo do cep
	 */
	public static String buscarCep(String cep) {
        String json;

        try {
            URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));

            json = jsonSb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return json;
    }
	

}