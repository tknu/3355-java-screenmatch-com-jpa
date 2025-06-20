package br.com.alura.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService("sk-proj-Neb41yMwZSoi0ljJtCVJU4-I_SqAMu0fUBDZs99mBGFaCgh5Clygo8" +
                "jYqza7hQDE2Af9e255dlT3BlbkFJkkefNbU8MgbbbdvVuwuK1Xs0Aqek1VCclGjYAraLX5WPzOHWVhR4tuFJRv4IouRGoss-21kdMA");

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}
