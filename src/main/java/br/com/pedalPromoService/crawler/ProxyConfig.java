package br.com.pedalPromoService.crawler;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ProxyConfig {
	
	public boolean defineJVMVariables(String proxy_host, String proxy_port, String proxy_user, String proxy_password, String no_proxy) throws Exception {
		boolean proxyHost = false;
		boolean proxyPort = false;
		boolean proxyUser = false;
		boolean proxyPassword = false;
		if (proxy_host != null && !"".equals(proxy_host.trim())) {
			System.setProperty("http.proxyHost",proxy_host);
			proxyHost = true;
		} else {
			System.out.println("Proxy Config: Propriedade http.proxyHost NAO encontrada.");
		}
		if (proxy_port != null && !"".equals(proxy_port.trim())) {
			System.setProperty("http.proxyPort",proxy_port);
			proxyPort = true;
		} else {
			System.out.println("Proxy Config: Propriedade http.proxyPort NAO encontrada.");
		}
		if (proxy_user != null && !"".equals(proxy_user.trim())) {
			System.setProperty("http.proxyUser",proxy_user);
			proxyUser = true;
		} else {
			System.out.println("Proxy Config: Propriedade http.proxyUser NAO encontrada.");
		}
		if (proxy_password != null && !"".equals(proxy_password.trim())) {
			System.setProperty("http.proxyPassword",proxy_password);
			proxyPassword = true;
		} else {
			System.out.println("Proxy Config: Propriedade http.proxyPassword NAO encontrada.");
		}
		if (no_proxy != null && !"".equals(no_proxy.trim())) {
			System.setProperty("http.nonProxyHosts",no_proxy);
		} else {
			System.out.println("Proxy Config: Propriedade http.nonProxyHosts NAO encontrada.");
		}
		return (proxyHost && proxyPort && proxyUser && proxyPassword);
	}
	
	public void changeAuthenticator() throws Exception {
		final String proxy_user = System.getProperty("http.proxyUser");
		final String proxy_password = System.getProperty("http.proxyPassword");
		if (proxy_user != null && !"".equals(proxy_user.trim()) && proxy_password != null && !"".equals(proxy_password.trim())) {
			System.out.println("Proxy Config: Definindo autenticador do proxy.");
			Authenticator.setDefault(
			   new Authenticator() {
			      @Override
			      public PasswordAuthentication getPasswordAuthentication() {
			         return new PasswordAuthentication(proxy_user,proxy_password.toCharArray());
			      }
			   }
			);
			System.out.println("Proxy Config: Autenticador definido.");
		} else {
			Authenticator.setDefault(null);
			if (proxy_user == null || "".equals(proxy_user.trim())) {
				System.out.println("Proxy Config: Propriedade http.proxyUser NAO encontrada.");
			}
			if (proxy_password == null || "".equals(proxy_password.trim())) {
				System.out.println("Proxy Config: Propriedade http.proxyPassword NAO encontrada.");
			}
			System.out.println("Proxy Config: Autenticador do proxy NAO definido.");
		}
	}
}
