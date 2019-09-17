package com.revolut.moneytransfer.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.revolut.moneytransfer.models.MoneyTransferResponse;

public final class ProcessUtil {
	private ProcessUtil() {

	}

	public static MoneyTransferResponse processResponse(HttpURLConnection conn) throws IOException {
		String contentType = conn.getContentType();

		System.out.println(contentType);

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = br.readLine();
		StringBuilder sb = new StringBuilder();

		while (line != null && !line.isEmpty()) {
			sb.append(line);
			line = br.readLine();
		}
		
		br.close();

		Gson gson = new Gson();

		return gson.fromJson(sb.toString(), MoneyTransferResponse.class);
	}
}
