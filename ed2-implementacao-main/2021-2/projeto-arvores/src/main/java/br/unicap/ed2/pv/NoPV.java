package br.unicap.ed2.pv;

import br.unicap.ed2.base.No;

public class NoPV extends No {

    private Cor cor;
  
   
    public NoPV(int chave) {
        super(chave);
        cor = Cor.RED;
    }


	public Cor getCor() {
		return cor;
	}


	public void setCor(Cor cor) {
		this.cor = cor;
	}
}

