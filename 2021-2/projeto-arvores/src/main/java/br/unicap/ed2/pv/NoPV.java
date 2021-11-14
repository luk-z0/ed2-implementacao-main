package br.unicap.ed2.pv;

import br.unicap.ed2.base.No;

public class NoPV {
	private int chave;
	private NoPV esquerda;
	private NoPV direita;
	private NoPV pai;
	private Cor cor;

	public NoPV(int chave) {
		this.chave = chave;
		pai = null;
		cor = Cor.RED;
	}

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

    public <T extends NoPV> void setEsquerda( T no) {
        esquerda = no;
    }

    @SuppressWarnings("unchecked")
    public <T extends NoPV> T getDireita() {
        return (T) direita;
    }

    @SuppressWarnings("unchecked")
    public <T extends NoPV> T getEsquerda() {
        return (T) esquerda;
    }

    public <T extends NoPV> void setDireita(T no) {
        direita = no;
    }

	public NoPV getPai() {
		return pai;
	}

	public void setPai(NoPV pai) {
		this.pai = pai;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public String toString() {
		return "No com chave - " + chave;
	}
}
