package br.unicap.ed2.pv;


public class ArvorePV<T extends NoPV> {

	protected NoPV raiz;

	public NoPV novoNode(int key) {

		return new NoPV(key);
	}

	public NoPV inserir(int key) {

		NoPV node = inserirNoHelper(raiz, key);
		
		return node;
	}

	private void verificarRegras(NoPV node) {
		
		NoPV outroNode ;
		
		while (node.getPai().getCor() == Cor.RED) {
			if (node.getPai() == node.getPai().getPai().getEsquerda()) {
				outroNode = node.getPai().getPai().getEsquerda();
				if (outroNode.getCor() == Cor.RED) {
					outroNode.setCor(Cor.BLACK);
					node.setCor(Cor.BLACK);
					node.getPai().getPai().setCor(Cor.RED);
					node = node.getPai().getPai();
				} else {
					if (node == node.getPai().getEsquerda()) {
						node = node.getPai();
						rotacaoDireita(node);
					}
					node.setCor(Cor.BLACK);
					node.getPai().getPai().setCor(Cor.RED);
					rotacaoDireita( node.getPai().getPai());
				}
			} else {
				outroNode = node.getPai().getPai().getDireita();

				if (outroNode.getCor() == Cor.RED) {
					outroNode.setCor(Cor.BLACK);
					node.setCor(Cor.BLACK);
					node.getPai().getPai().setCor(Cor.RED);
					node = node.getPai().getPai();
				} else {
					if (node == node.getPai().getDireita()) {
						node = node.getPai();
						rotacaoEsquerda(node);
					}
					node.setCor(Cor.BLACK);
					node.getPai().getPai().setCor(Cor.RED);
					rotacaoDireita(node.getPai().getPai());
				}
			}
			if (node == raiz) {
				break;
			}
		}
		raiz.setCor(Cor.BLACK);

	}

	public NoPV procurar(int key) {
		return procurarNoHelper(raiz, key);
	}

	public void deletar(int key) {
		// raiz = deletarNoHelper(raiz, key);
		// alteracao
	}

	public void rotacaoDireita(NoPV node) {

		NoPV esquerda = node.getEsquerda();
		node.setEsquerda(esquerda.getDireita());

		if (node.getEsquerda() != null) {
			node.getEsquerda().setPai(node);

		}

		esquerda.setPai(node.getPai());

		if (esquerda.getPai() == null) {
			raiz = esquerda;
		} else if (node == node.getPai().getEsquerda()) {
			node.getPai().setEsquerda(esquerda);
		} else {
			node.getPai().setDireita(esquerda);
		}

		esquerda.setDireita(node);
		node.setPai(esquerda);

	}

	public void rotacaoEsquerda(NoPV node) {

		NoPV direita = node.getDireita();
		node.setDireita(direita.getEsquerda());

		if (node.getDireita() != null) {
			node.getDireita().setPai(node);

		}

		direita.setPai(node.getPai());

		if (direita.getPai() == null) {
			raiz = direita;
		} else if (node == node.getPai().getEsquerda()) {
			node.getPai().setEsquerda(direita);
		} else {
			node.getPai().setDireita(direita);
		}

		direita.setEsquerda(node);
		node.setPai(direita);

	}

	public void clear() {
		raiz = null;
	}

	public boolean ehRaiz(int i) {
		if (raiz == null) {
			return false;
		}
		return ((NoPV) raiz).getChave() == i;
	}

	protected NoPV inserirNoHelper(NoPV atual, int chave) {
		NoPV node = novoNode(chave);
		node.setChave(chave);

		NoPV folhaCorreta = null;
		// No atual = this.raiz;

		while (atual != null) {
			folhaCorreta = atual;
			if (node.getChave() < atual.getChave()) {
				atual = atual.getEsquerda();
			} else {
				atual = atual.getDireita();
			}
		}

		// <<folhaCorreta>> é pai de <<node>>
		node.setPai(folhaCorreta);
		if (folhaCorreta == null) {
			raiz = node;
		} else if (node.getChave() < folhaCorreta.getChave()) {
			folhaCorreta.setEsquerda(node);
		} else {
			folhaCorreta.setDireita(node);
		}
		if (node.getPai() == null) {
			node.setCor(Cor.BLACK);
			node.setPai(node);
			
		}
		verificarRegras(node);
		return node;
	}

	protected NoPV procurarNoHelper(NoPV aux, int element) {
		while (aux != null) {
			if (aux.getChave() == element)
				return aux;
			if (element < aux.getChave()) {
				if (aux.getEsquerda() == null) {
					return null;
				}
				aux = aux.getEsquerda();
			}
			if (element > aux.getChave()) {
				if (aux.getDireita() == null) {
					return null;
				}
				aux = aux.getDireita();
			}
		}
		return null;
	}

}