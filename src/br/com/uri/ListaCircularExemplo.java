package br.com.uri;

public class ListaCircularExemplo {
	
	public static void main(String[] args) {
		ListaCircular circular = new ListaCircular();

        for (int i = 0; i < 15; i++) {
            No node = new No();
            node.setId(i);
            node.setCrianca(new Crianca(1, "Teste"+i));
            circular.add(node);
            
        }

//        while (circular.getNo() != null) {
//            System.out.println(circular.getNo().getId() +","+ circular.getNo().getCrianca().getNome());
//            circular.proximoNo();
//        }
        
        
        for (int i = 0; i < 20; i++) {
            System.out.println(circular.getNo().getId() +","+ circular.getNo().getCrianca().getNome());
            circular.anteriorNo();
        }
	}
}