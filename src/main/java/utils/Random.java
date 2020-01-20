package utils;

public class Random {

	public String getAlphaNumericString(int n) {

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			int index = (int) (AlphaNumericString.length() * Math.random());

			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public String getRandomInteger(int min, int max) {
		int x = (int) (Math.random() * ((max - min) + 1)) + min;
		return x + "";
	}

}
