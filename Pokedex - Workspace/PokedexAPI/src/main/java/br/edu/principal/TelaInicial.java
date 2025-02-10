package br.edu.principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class TelaInicial extends JFrame {
    private JTextField campoPesquisa;
    private JLabel imagemPokemon;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public TelaInicial() {
        setTitle("Pokédex");
        setSize(1000, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal com CardLayout
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Tela Inicial
        JPanel telaInicial = criarTelaInicial();
        cardPanel.add(telaInicial, "TelaInicial");

        // Adiciona o painel de cards ao frame
        add(cardPanel);

        setVisible(true);
    }

    private JPanel criarTelaInicial() {
        JPanel telaInicial = new JPanel(new BorderLayout());

        // Painel superior
        JPanel topo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Font fonte = new Font("Arial", Font.BOLD, 60);
                g2d.setFont(fonte);
                String texto = "Pokédex";

                int x = getWidth() / 2 - g2d.getFontMetrics().stringWidth(texto) / 2;
                int y = 70;

                g2d.setColor(Color.BLUE);
                for (int i = -2; i <= 2; i++) {
                    for (int j = -2; j <= 2; j++) {
                        g2d.drawString(texto, x + i, y + j);
                    }
                }

                g2d.setColor(Color.YELLOW);
                g2d.drawString(texto, x, y);
            }
        };
        topo.setBackground(new Color(50, 50, 50));
        topo.setPreferredSize(new Dimension(getWidth(), 100));
        telaInicial.add(topo, BorderLayout.NORTH);

        // Painel de pesquisa
        JPanel painelPesquisa = new JPanel();
        painelPesquisa.setLayout(new FlowLayout());

        JLabel lblPesquisa = new JLabel("Nome ou número");
        lblPesquisa.setFont(new Font("Arial", Font.BOLD, 16));
        lblPesquisa.setForeground(Color.WHITE);

        campoPesquisa = new JTextField(30);
        campoPesquisa.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton botaoBuscar = new JButton("Buscar");
        botaoBuscar.setFont(new Font("Arial", Font.BOLD, 16));
        botaoBuscar.setBackground(new Color(0, 150, 0));
        botaoBuscar.setForeground(Color.WHITE);
        botaoBuscar.setFocusPainted(false);
        botaoBuscar.setBorderPainted(false);
        botaoBuscar.setPreferredSize(new Dimension(100, 40));

        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomePokemon = campoPesquisa.getText().trim();
                if (!nomePokemon.isEmpty()) {
                    // Exibe uma tela de carregamento
                    JPanel telaCarregamento = new JPanel(new BorderLayout());
                    telaCarregamento.setBackground(new Color(40, 40, 40));
                    JLabel lblCarregando = new JLabel("Carregando...", SwingConstants.CENTER);
                    lblCarregando.setForeground(Color.WHITE);
                    lblCarregando.setFont(new Font("Arial", Font.BOLD, 24));
                    telaCarregamento.add(lblCarregando, BorderLayout.CENTER);
                    cardPanel.add(telaCarregamento, "TelaCarregamento");
                    cardLayout.show(cardPanel, "TelaCarregamento");

                    // Busca o Pokémon em uma thread separada
                    new Thread(() -> {
                        TelaResultado telaResultado = new TelaResultado(nomePokemon, cardPanel, cardLayout);
                        cardPanel.add(telaResultado, "TelaResultado");
                        cardLayout.show(cardPanel, "TelaResultado");
                    }).start();
                } else {
                    JOptionPane.showMessageDialog(TelaInicial.this, "Digite um nome ou número de Pokémon.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton botaoFiltro = new JButton("Filtro");
        botaoFiltro.setFont(new Font("Arial", Font.BOLD, 16));
        botaoFiltro.setBackground(new Color(255, 140, 0));
        botaoFiltro.setForeground(Color.WHITE);
        botaoFiltro.setFocusPainted(false);
        botaoFiltro.setBorderPainted(false);
        botaoFiltro.setPreferredSize(new Dimension(100, 40));
        JButton btnFiltro = new JButton("Filtro");
        
        botaoFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirJanelaFiltro();
            }
        });
        painelPesquisa.setBackground(new Color(40, 40, 40));
        painelPesquisa.add(lblPesquisa);
        painelPesquisa.add(campoPesquisa);
        painelPesquisa.add(botaoBuscar);
        painelPesquisa.add(botaoFiltro);

        telaInicial.add(painelPesquisa, BorderLayout.CENTER);

        // Espaço para imagem
        JPanel painelImagem = new JPanel();
        painelImagem.setBackground(new Color(40, 40, 40)); // Cor igual ao fundo

        imagemPokemon = new JLabel();
        imagemPokemon.setHorizontalAlignment(SwingConstants.CENTER);
        imagemPokemon.setOpaque(false); // Mantém a transparência da imagem

        painelImagem.add(imagemPokemon);
        telaInicial.add(painelImagem, BorderLayout.SOUTH);

        carregarImagem("IMG/victini.png");

        return telaInicial;
    }

    private void carregarImagem(String caminho) {
        ImageIcon icone = new ImageIcon(caminho);
        Image imagem = icone.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        imagemPokemon.setIcon(new ImageIcon(imagem));
    }
    private void abrirJanelaFiltro() {
        JPanel painelFiltro = new JPanel(new GridLayout(5, 2, 5, 5));
        painelFiltro.setBackground(new Color(40, 40, 40));

        JTextField txtNome = new JTextField();
        JTextField txtHabilidade = new JTextField();
        JTextField txtTipo1 = new JTextField();
        JTextField txtTipo2 = new JTextField();

        // Cria os labels com texto branco
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setForeground(Color.WHITE);
        JLabel lblHabilidade = new JLabel("Habilidade:");
        lblHabilidade.setForeground(Color.WHITE);
        JLabel lblTipo1 = new JLabel("Tipo 1:");
        lblTipo1.setForeground(Color.WHITE);
        JLabel lblTipo2 = new JLabel("Tipo 2:");
        lblTipo2.setForeground(Color.WHITE);

        // Adiciona os componentes ao painel
        painelFiltro.add(lblNome);
        painelFiltro.add(txtNome);
        painelFiltro.add(lblHabilidade);
        painelFiltro.add(txtHabilidade);
        painelFiltro.add(lblTipo1);
        painelFiltro.add(txtTipo1);
        painelFiltro.add(lblTipo2);
        painelFiltro.add(txtTipo2);

        int resultado = JOptionPane.showConfirmDialog(this, painelFiltro, "Filtrar Pokémon", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            String nome = txtNome.getText().trim();
            String habilidade = txtHabilidade.getText().trim();
            String tipo1 = txtTipo1.getText().trim();
            String tipo2 = txtTipo2.getText().trim();

            aplicarFiltros(nome, habilidade, tipo1, tipo2);
        }
    }
    private void aplicarFiltros(String nome, String habilidade, String tipo1, String tipo2) {
        PokemonSearch buscador = new PokemonSearch();
    	List<Pokemon> resultadoFiltrado = buscador.searchForAllAtributes(buscador.selectAllPokemon(), nome, habilidade, tipo1, "");

        // Segunda busca com tipo2, se necessário
        if (!tipo2.isEmpty()) {
            resultadoFiltrado = buscador.searchForAllAtributes(resultadoFiltrado, nome, habilidade, tipo2, "");
        }

        // Exibir os resultados filtrados
        exibirResultadosFiltrados(resultadoFiltrado);
    }
    private void exibirResultadosFiltrados(List<Pokemon> pokemonsFiltrados) {
        JPanel painelResultados = new JPanel();
        painelResultados.setLayout(new BoxLayout(painelResultados, BoxLayout.Y_AXIS));
        painelResultados.setBackground(new Color(40, 40, 40));

        for (Pokemon pokemon : pokemonsFiltrados) {
            JLabel lblPokemon = new JLabel(pokemon.getName() + " (ID: " + pokemon.getId() + ")");
            lblPokemon.setForeground(Color.WHITE);
            painelResultados.add(lblPokemon);
        }

        JScrollPane scrollPane = new JScrollPane(painelResultados);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JOptionPane.showMessageDialog(this, scrollPane, "Resultados da Busca", JOptionPane.PLAIN_MESSAGE);
    }
    public static void main(String[] args) {
        new TelaInicial();
    }
}