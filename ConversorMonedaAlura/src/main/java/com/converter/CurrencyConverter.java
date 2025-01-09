package com.converter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyConverter {

    // URL de la API con la clave API Exchangerate embebida.
    private static final String BASE_URL = {EXCHANGE_RATE-API-KEY};

    public static double getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            // Construyendo la URL para obtener tasas de cambio desde la API Exchangerate (En este caso).
            String url = BASE_URL + baseCurrency;

            // Creando cliente HTTP para las solicitudes que realizará el usuario.
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Enviando la solicitud al usuario.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificando si hay respuesta exitosa.
            if (response.statusCode() != 200) {
                throw new Exception("Error en la solicitud HTTP: " + response.statusCode());
            }

            // Analizando respuesta JSON.
            JsonObject responseObject = JsonParser.parseString(response.body()).getAsJsonObject();

            // Comprobando si el campo 'conversion_rates' se encuentra en la respuesta.
            if (!responseObject.has("conversion_rates")) {
                throw new Exception("No se han encontrado las tasas de conversión en la respuesta.");
            }

            // Obteniendo las tasas de cambio desde el objeto 'conversion_rates'.
            JsonObject rates = responseObject.getAsJsonObject("conversion_rates");

            // Verificando si la moneda destino existe en las tasas de conversión.
            if (rates.has(targetCurrency)) {
                return rates.get(targetCurrency).getAsDouble();
            } else {
                throw new Exception("Disculpa, la tasa de cambio no se encuentra para " + targetCurrency);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
            return -1;
        }
    }

    public static double convert(double amount, String baseCurrency, String targetCurrency) {
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
        if (exchangeRate == -1) {
            System.out.println("Error al obtener tasa de cambio. La conversión ha fallado, vuelva a intentarlo nuevamente.");
            return -1;
        }
        return amount * exchangeRate;
    }
}
