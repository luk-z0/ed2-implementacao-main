package br.unicap.ed2.avl;

import br.unicap.ed2.base.AbstractArvoreBinariaDePesquisa;

public class ArvoreAVL extends AbstractArvoreBinariaDePesquisa<NoAVL> {

    @Override
    public NoAVL novoNode(int key) {
    	return new NoAVL(key);
    }

    @Override
    public NoAVL inserir(int key) {
        NoAVL node =  inserirNoHelper(raiz, key);
        raiz =  inserirNoHelper(raiz, node.getChave());
        return verificarBalanceamento(node);
    }

    @Override
    public NoAVL procurar(int key) {
        return procurarNoHelper(raiz, key);
    }

    @Override
    public void deletar(int key) {
        raiz = deletarNoHelper(raiz, key);
    }
  
   
    public NoAVL rotacaoEsquerda(NoAVL node) {

    	NoAVL direita = node.getDireita();
		direita.setPai(node.getPai());

		node.setDireita(direita.getEsquerda());

		if (node.getDireita() != null) {
			node.getDireita().setPai(node);
		}

		direita.setEsquerda(node);
		node.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireita() == node) {
				direita.getPai().setDireita(direita);
			
			} else if (direita.getPai().getEsquerda() == node) {
				direita.getPai().setEsquerda(direita);
			}
		}

		setBalanceamento(node);
		setBalanceamento(direita);

		return direita;
	}

	public NoAVL rotacaoDireita(NoAVL node) {

		NoAVL esquerda = node.getEsquerda();
		esquerda.setPai(node.getPai());

		node.setEsquerda(esquerda.getDireita());

		if (node.getEsquerda() != null) {
			node.getEsquerda().setPai(node);
		}

		esquerda.setDireita(node);
		node.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireita() == node) {
				esquerda.getPai().setDireita(esquerda);
			
			} else if (esquerda.getPai().getEsquerda() == node) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}

		setBalanceamento(node);
		setBalanceamento(esquerda);

		return esquerda;
	}

	public NoAVL duplaRotacaoEsquerdaDireita(NoAVL node) {
		node.setEsquerda(rotacaoEsquerda(node.getEsquerda()));
		return rotacaoDireita(node);
	}

	public NoAVL duplaRotacaoDireitaEsquerda(NoAVL node) {
		node.setDireita(rotacaoDireita(node.getDireita()));
		return rotacaoEsquerda(node);
	}
	
	private void setBalanceamento(NoAVL node) {
		node.setFatorBalanceamento(altura(node.getDireita()) - altura(node.getEsquerda()));
	}
	
	public NoAVL verificarBalanceamento(NoAVL node) {
		setBalanceamento(node);
		int balanceamento = node.getFatorBalanceament();

		if (balanceamento == -2) {

			if (altura(node.getEsquerda().getEsquerda()) >= altura(node.getEsquerda().getDireita())) {
				node = rotacaoDireita(node);

			} else {
				node = duplaRotacaoEsquerdaDireita(node);
			}

		} else if (balanceamento == 2) {

			if (altura(node.getDireita().getDireita()) >= altura(node.getDireita().getEsquerda())) {
				node = rotacaoEsquerda(node);

			} else {
				node = duplaRotacaoDireitaEsquerda(node);
			}
		}

		if (node.getPai() != null) {
			verificarBalanceamento(node.getPai());
		} else {
			this.raiz = node;
		}
		
		return node;
	}
   
}