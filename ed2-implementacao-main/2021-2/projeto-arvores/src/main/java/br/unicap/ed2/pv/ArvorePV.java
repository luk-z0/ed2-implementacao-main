package br.unicap.ed2.pv;

import br.unicap.ed2.base.AbstractArvoreBinariaDePesquisa;


public class ArvorePV extends AbstractArvoreBinariaDePesquisa<NoPV> {

	@Override
	public NoPV novoNode(int key) {

		return new NoPV(key);
	}

	@Override
	public NoPV inserir(int key) {

		NoPV node = inserirNoHelper(raiz, key);
		NoPV nodeX = raiz;
		NoPV nodeY = null;
		
		while (nodeX != null) {
			nodeY = nodeX;
			if (node.getChave() < nodeX.getChave()) {
				nodeX.setEsquerda(nodeX.getEsquerda());
			} else {
				nodeX.setDireita(nodeX.getDireita());
			}
		}
		node.setPai(nodeY);
		if (nodeY == null) {
			raiz = node;
		} else if (node.getChave() < nodeY.getChave()) {
			nodeY.setEsquerda(node);
		} else {
			nodeY.setDireita(node);
		}

		if (node.getPai() == null) {
			node.setCor(Cor.BLACK);
		}

		verificarRegras(node);
		return node;

	}

	private void verificarRegras(NoPV node) {
				
		NoPV node3 = novoNode(node.getChave());
		node3.setCor(Cor.RED );
		node3.setPai(node3);
		
		
    	NoPV paiNode = null;
    	NoPV avoNode = null;
     	  	

    	while(node3.getCor() != Cor.BLACK) {
    		
    		paiNode= node3.getPai();
    		avoNode = node3.getPai().getPai();
    		
    		if (paiNode == avoNode.getEsquerda()) {
    			
    			NoPV tioNode = avoNode.getDireita();
    			
    			if (tioNode != null && tioNode.getCor() == Cor.RED) {
    				avoNode.setCor(Cor.RED);
    				paiNode.setCor(Cor.BLACK);
    				tioNode.setCor(Cor.BLACK);
    				node3 = avoNode; 
    			}else {
    				
    				if (node3 == paiNode.getDireita()) {
    					rotacaoEsquerda(paiNode);
    					node3 = paiNode;
    					paiNode = node3.getPai();		
					}
    				
    				rotacaoDireita(avoNode);
    				Cor tempCor = paiNode.getCor();
    				paiNode.setCor(avoNode.getCor());
    				avoNode.setCor(tempCor);
    				node3 = paiNode;
    			}	
				
			}else {
				NoPV tioNode = avoNode.getEsquerda();
				
				if (tioNode != null && tioNode.getCor() == Cor.RED) {
    				avoNode.setCor(Cor.RED);
    				paiNode.setCor(Cor.BLACK);
    				tioNode.setCor(Cor.BLACK);
    				node3 = avoNode; 
    			}else {
    				if (node == paiNode.getEsquerda()) {
						rotacaoDireita(paiNode);
						node3 = paiNode;
						paiNode = node.getPai();
					}
    				
    				rotacaoEsquerda(avoNode);
    				Cor tempCor = paiNode.getCor();
    				paiNode.setCor(avoNode.getCor());
    				avoNode.setCor(tempCor);
    				node3 = paiNode;
    			}
			}
    	}
    	
    	if (raiz.getCor() == Cor.RED) {
			raiz.setCor(Cor.BLACK);
		}
    	
	}



	@Override
	public NoPV procurar(int key) {
		return procurarNoHelper(raiz, key);
	}

	@Override
	public void deletar(int key) {
		raiz = deletarNoHelper(raiz, key);
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
}