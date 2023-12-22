package br.com.challenge.domains;

public enum DocumentType {
	
	CPF, CNPJ;
	
	public boolean isMerchant() {
		return this.equals(DocumentType.CNPJ);
	}
}
