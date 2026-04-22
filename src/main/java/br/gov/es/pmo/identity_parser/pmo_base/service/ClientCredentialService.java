/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.gov.es.pmo.identity_parser.pmo_base.service;

import br.gov.es.pmo.identity_parser.pmo_base.properties.ClientCredentialProperties;
import java.util.Optional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;

/**
 *
 * @author gean.carneiro
 */
@Service
public class ClientCredentialService {
    
    private static final String SUFIX = ClientCredentialProperties.CLIENT_SUFIX;
    
    private final OAuth2AuthorizedClientManager authorizedClientManager;
    
    private final ClientRegistrationRepository clientRegistrationRepository;
    
    public ClientCredentialService(
        final OAuth2AuthorizedClientManager authorizedClientManager,
        final ClientRegistrationRepository clientRegistrationRepository
    ){
        this.authorizedClientManager = authorizedClientManager;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }
    
    public String getClientToken(String registrationId) {
        
        final String registrationIdFull = registrationId + SUFIX;
        
        
        
        ClientRegistration registration = clientRegistrationRepository.findByRegistrationId(registrationIdFull);
        
        if(registration == null) return null;
                
        OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest.withClientRegistrationId(registrationIdFull)
                                            .principal(new UsernamePasswordAuthenticationToken("System", "N/A"))
                                            .build();
        
        
        return Optional.ofNullable(authorizedClientManager.authorize(request))
                        .map(OAuth2AuthorizedClient::getAccessToken)
                        .map(OAuth2AccessToken::getTokenValue)
                        .orElseThrow(() -> new IllegalStateException("Erro ao requisitar o client token da API para: " + registrationIdFull));
                
    }
    
    
}
