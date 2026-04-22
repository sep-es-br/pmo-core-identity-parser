/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.gov.es.pmo.identity_parser.pmo_base.model;

import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 *
 * @author gean.carneiro
 * 
 * @param <ID> o tipo do id/sub utilizado pra identificar o usuario
 * 
 */
public interface IIdentityParser<ID> {
    
    /**
     * retorna o identificador do usuario, pode ser id,
     * sub, cpf, o que a api usar pra identificar
     * 
     * @param user usuario retornado pelo user-info
     * @param authToken token de autenticação
     * @param clientToken token de Client Credential
     * @return id/sub do usuário
     */
    public ID getId(OAuth2User user, String authToken, String clientToken);
    
    /**
     * retorna o nome do usuario
     * 
     * @param user usuario retornado pelo user-info
     * @param authToken token de autenticação
     * @param clientToken token de Client Credential
     * @return nome do usuário
     */    
    public String getNome(OAuth2User user, String authToken, String clientToken);
    
    /**
     * retorna o email do usuario
     * 
     * @param user usuario retornado pelo user-info
     * @param authToken token de autenticação
     * @param clientToken token de Client Credential
     * @return nome do usuário
     */    
    public String getEmail(OAuth2User user, String authToken, String clientToken);
    
    /**
     * retorna uma propriedade especifica do usuario
     * 
     * @param user usuario retornado pelo user-info
     * @param authToken token de autenticação
     * @param clientToken token de Client Credential
     * @param attr atributo que deseja recuperar do usuario
     * @return 
     */    
    default Object getAttribute(OAuth2User user, String authToken, String clientToken, String attr) {
        return user.getAttribute(attr);
    }

    
}
