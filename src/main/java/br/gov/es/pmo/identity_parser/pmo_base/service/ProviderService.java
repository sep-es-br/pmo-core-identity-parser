/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.gov.es.pmo.identity_parser.pmo_base.service;

import br.gov.es.pmo.identity_parser.pmo_base.model.IIdentityParser;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 *
 * @author gean.carneiro
 */
/**
 *
 * @author gean.carneiro
 */
@Service
public class ProviderService {
    
    private final Map<String, IIdentityParser<?>> providers;
    
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final ClientCredentialService clientCredentialService;
        
    public ProviderService(
        final Map<String, IIdentityParser<?>> providers,
        final OAuth2AuthorizedClientService authorizedClientService,
        final ClientCredentialService clientCredentialService
    ){
        this.providers = providers;
        this.authorizedClientService = authorizedClientService;
        this.clientCredentialService = clientCredentialService;
    }
    
    private OAuth2AuthenticationToken getToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof OAuth2AuthenticationToken) {
            return (OAuth2AuthenticationToken) auth;
        }

        throw new IllegalStateException("Usuario não autenticado");
    }

    private String getRegistrationId() {
        return getToken().getAuthorizedClientRegistrationId();
    }

    private OAuth2User getUser() {
        return getToken().getPrincipal();
    }
    
    private String getAccessToken() {
        OAuth2AuthenticationToken authToken = getToken();
        
        return authorizedClientService.loadAuthorizedClient(
                authToken.getAuthorizedClientRegistrationId(),
                authToken.getName()
        ).getAccessToken().getTokenValue();
    }
    
    private String getClientToken() {
        return clientCredentialService.getClientToken(this.getRegistrationId());
    }
       
    
    
    public IIdentityParser<?> getProvider() {
        
        IIdentityParser<?> provider = this.providers.get(this.getRegistrationId());
        
        if(provider == null) {
            throw new IllegalStateException("Provider não implementado: " + getRegistrationId() );
        }

        
        return provider;
                    
    }
    
    public Object getAttribute(String attr) {
        return getProvider().getAttribute(this.getUser(), getAccessToken(), getClientToken() , attr);
    }
    
    public <T> T getAttribute(String attr, Class<T> type) {
        return type.cast(getAttribute(attr));
    }
    
    public Object getId(){
        return this.getProvider().getId(this.getUser(), getAccessToken(), getClientToken());
    }
    
    public String getNome() {
        return this.getProvider().getNome(this.getUser(), getAccessToken(), getClientToken());
    }
    
    public String getEmail() {
        return this.getProvider().getEmail(this.getUser(), getAccessToken(), getClientToken());
    }
    
    
}