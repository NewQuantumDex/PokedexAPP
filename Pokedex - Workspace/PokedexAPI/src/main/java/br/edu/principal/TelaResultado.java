package br.edu.principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class TelaResultado extends JPanel {
    private PokedexAPI api;
    private Pokemon pokemon;
    private Map<String, Color> coresTipos;
    private JPanel parentPanel;
    private CardLayout cardLayout;

    public TelaResultado(String nomePokemon, JPanel parentPanel, CardLayout cardLayout) {
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        api = new PokedexAPI();
        pokemon = api.GET_POKEMON(nomePokemon.toLowerCase());

        setLayout(new BorderLayout());
        setBackground(new Color(40, 40, 40)); 
        definirCoresTipos();

        if (pokemon != null) {
        	JPanel panel = new JPanel();
        	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        	panel.setBackground(new Color(40, 40, 40));
        	panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30)); // Margens: top, left, bottom, right
            

            JLabel lblNome = new JLabel("Name: " + pokemon.getName() + " (ID: " + pokemon.getId() + ")");
            lblNome.setForeground(Color.WHITE);
            lblNome.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel lblAltura = new JLabel("Height: " + pokemon.getHeight() + " ft");
            lblAltura.setForeground(Color.WHITE);
            lblAltura.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel lblPeso = new JLabel("Weight: " + pokemon.getWeight() + " lbs");
            lblPeso.setForeground(Color.WHITE);
            lblPeso.setAlignmentX(Component.LEFT_ALIGNMENT);

            ImageIcon sprite = null;
            if (pokemon.getSprites() != null && pokemon.getSprites().getFront_default() != null) {
                try {
                    sprite = new ImageIcon(new java.net.URL(pokemon.getSprites().getFront_default()));
                } catch (Exception e) {
                    e.printStackTrace();
                    sprite = new ImageIcon("IMG/default.png"); 
                }
            } else {
                sprite = new ImageIcon("IMG/default.png"); 
            }

            Image image = sprite.getImage();
            Image newimg = image.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH); 
            sprite = new ImageIcon(newimg);

            JLabel lblImagem = new JLabel(sprite);
            lblImagem.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            JLabel lblTipos = new JLabel("Types");
            lblTipos.setForeground(Color.WHITE);
            lblTipos.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel painelTipos = new JPanel();
            painelTipos.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
            painelTipos.setBackground(new Color(40, 40, 40));
            painelTipos.setAlignmentX(Component.LEFT_ALIGNMENT);

            if (pokemon.getTypes() != null) {
                for (br.edu.principal.Type type : pokemon.getTypes()) {
                    String tipoNome = type.getType().getName().toUpperCase();
                    Color corTipo = coresTipos.getOrDefault(tipoNome, Color.GRAY);
                    JLabel lblTipo = new JLabel(tipoNome, SwingConstants.CENTER);
                    lblTipo.setOpaque(true);
                    lblTipo.setBackground(corTipo);
                    lblTipo.setForeground(Color.WHITE);
                    lblTipo.setPreferredSize(new Dimension(60, 25));
                    painelTipos.add(lblTipo);
                }
            }

            JLabel lblFraquezas = new JLabel("Weakness");
            lblFraquezas.setForeground(Color.WHITE);
            lblFraquezas.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel painelFraquezas = new JPanel();
            painelFraquezas.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
            painelFraquezas.setBackground(new Color(40, 40, 40));
            painelFraquezas.setAlignmentX(Component.LEFT_ALIGNMENT);

            if (pokemon.getWeakness() != null) {
                List<String> fraquezas = new ArrayList<>();
                for (br.edu.principal.TypeDetails weakness : pokemon.getWeakness()) {
                    fraquezas.add(weakness.getName().toUpperCase());
                }
                Map<String, Integer> contagemFraquezas = contarRepeticoes(fraquezas);

                for (Map.Entry<String, Integer> entry : contagemFraquezas.entrySet()) {
                    String fraquezaNome = entry.getKey();
                    int contagem = entry.getValue();
                    if (contagem > 1) {
                        fraquezaNome = "*" + fraquezaNome; 
                    }
                    Color corFraqueza = coresTipos.getOrDefault(fraquezaNome.replace("*", ""), Color.GRAY);
                    JLabel lblFraqueza = new JLabel(fraquezaNome, SwingConstants.CENTER);
                    lblFraqueza.setOpaque(true);
                    lblFraqueza.setBackground(corFraqueza);
                    lblFraqueza.setForeground(Color.WHITE);
                    lblFraqueza.setPreferredSize(new Dimension(60, 25));
                    painelFraquezas.add(lblFraqueza);
                }
            }

            JLabel lblResistencias = new JLabel("Resistances");
            lblResistencias.setForeground(Color.WHITE);
            lblResistencias.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel painelResistencias = new JPanel();
            painelResistencias.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
            painelResistencias.setBackground(new Color(40, 40, 40));
            painelResistencias.setAlignmentX(Component.LEFT_ALIGNMENT);

            if (pokemon.getResistance() != null) {
                List<String> resistencias = new ArrayList<>();
                for (br.edu.principal.TypeDetails resistance : pokemon.getResistance()) {
                    resistencias.add(resistance.getName().toUpperCase());
                }
                Map<String, Integer> contagemResistencias = contarRepeticoes(resistencias);

                for (Map.Entry<String, Integer> entry : contagemResistencias.entrySet()) {
                    String resistenciaNome = entry.getKey();
                    int contagem = entry.getValue();
                    if (contagem > 1) {
                        resistenciaNome = "*" + resistenciaNome; 
                    }
                    Color corResistencia = coresTipos.getOrDefault(resistenciaNome.replace("*", ""), Color.GRAY);
                    JLabel lblResistencia = new JLabel(resistenciaNome, SwingConstants.CENTER);
                    lblResistencia.setOpaque(true);
                    lblResistencia.setBackground(corResistencia);
                    lblResistencia.setForeground(Color.WHITE);
                    lblResistencia.setPreferredSize(new Dimension(60, 25));
                    painelResistencias.add(lblResistencia);
                }
            }

            JLabel lblImunidades = new JLabel("Immunities");
            lblImunidades.setForeground(Color.WHITE);
            lblImunidades.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel painelImunidades = new JPanel();
            painelImunidades.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
            painelImunidades.setBackground(new Color(40, 40, 40));
            painelImunidades.setAlignmentX(Component.LEFT_ALIGNMENT);

            if (pokemon.getImmunity() != null) {
                for (br.edu.principal.TypeDetails immunity : pokemon.getImmunity()) {
                    String imunidadeNome = immunity.getName().toUpperCase();
                    Color corImunidade = coresTipos.getOrDefault(imunidadeNome, Color.GRAY);
                    JLabel lblImunidade = new JLabel(imunidadeNome, SwingConstants.CENTER);
                    lblImunidade.setOpaque(true);
                    lblImunidade.setBackground(corImunidade);
                    lblImunidade.setForeground(Color.WHITE);
                    lblImunidade.setPreferredSize(new Dimension(60, 25));
                    painelImunidades.add(lblImunidade);
                }
            }

            JLabel lblStatus = new JLabel("Stats");
            lblStatus.setForeground(Color.WHITE);
            lblStatus.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel painelStatus = new JPanel();
            painelStatus.setLayout(new BoxLayout(painelStatus, BoxLayout.Y_AXIS));
            painelStatus.setBackground(new Color(40, 40, 40));
            painelStatus.setAlignmentX(Component.LEFT_ALIGNMENT);

            if (pokemon.getStats() != null) {
                for (br.edu.principal.Stat stat : pokemon.getStats()) {
                    String nomeStat = stat.getStat().getName().toUpperCase();
                    int valorStat = stat.getBase_stat();
                    JLabel lblStat = new JLabel(nomeStat + ": " + valorStat);
                    lblStat.setForeground(Color.WHITE);
                    lblStat.setAlignmentX(Component.LEFT_ALIGNMENT);
                    painelStatus.add(lblStat);
                }
            }

            JLabel lblDescricao = new JLabel("Description");
            lblDescricao.setForeground(Color.WHITE);
            lblDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);

            JTextArea txtDescricao = new JTextArea();
            txtDescricao.setForeground(Color.WHITE);
            txtDescricao.setBackground(new Color(40, 40, 40));
            txtDescricao.setLineWrap(true);
            txtDescricao.setWrapStyleWord(true);
            txtDescricao.setEditable(false);
            txtDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);

            if (pokemon.getSpecies() != null && pokemon.getSpecies().getSpecies() != null) {
                PokemonTextEntry descricao = pokemon.getSpecies().getSpecies().getFlavor_text_entries().get(0);
                txtDescricao.setText(descricao.getFlavor_text());
            }

            JLabel lblHabilidades = new JLabel("Abilities");
            lblHabilidades.setForeground(Color.WHITE);
            lblHabilidades.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel painelHabilidades = new JPanel();
            painelHabilidades.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
            painelHabilidades.setBackground(new Color(60, 60, 60));
            painelHabilidades.setAlignmentX(Component.LEFT_ALIGNMENT);

            if (pokemon.getAbilities() != null) {
                for (br.edu.principal.Ability ability : pokemon.getAbilities()) {
                    String nomeHabilidade = ability.getAbility().getName().toUpperCase();
                    JButton btnHabilidade = new JButton(nomeHabilidade);
                    btnHabilidade.setBackground(new Color(80, 80, 80));
                    btnHabilidade.setForeground(Color.WHITE);
                    btnHabilidade.setFocusPainted(false);
                    btnHabilidade.setBorderPainted(false);
                    btnHabilidade.setPreferredSize(new Dimension(120, 30));
                    btnHabilidade.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String descricaoHabilidade = ability.getAbility().getAbilityEffect().getEffect_entries().get(1).getShort_effect();
                            JOptionPane.showMessageDialog(TelaResultado.this, descricaoHabilidade, "Ability: " + nomeHabilidade, JOptionPane.INFORMATION_MESSAGE);
                        }
                    });
                    painelHabilidades.add(btnHabilidade);
                }
            }

            JLabel lblEvolucoes = new JLabel("Evolution Line");
            lblEvolucoes.setForeground(Color.WHITE);
            lblEvolucoes.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel painelEvolucoes = new JPanel();
            painelEvolucoes.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
            painelEvolucoes.setBackground(new Color(60, 60, 60));
            painelEvolucoes.setAlignmentX(Component.LEFT_ALIGNMENT);

            if (pokemon.getEvolutionNames() != null) {
                for (String evolucao : pokemon.getEvolutionNames()) {
                    JButton btnEvolucao = new JButton(evolucao);
                    btnEvolucao.setBackground(new Color(80, 80, 80));
                    btnEvolucao.setForeground(Color.WHITE);
                    btnEvolucao.setFocusPainted(false);
                    btnEvolucao.setBorderPainted(false);
                    btnEvolucao.setPreferredSize(new Dimension(120, 30));
                    btnEvolucao.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JPanel telaCarregamento = new JPanel(new BorderLayout());
                            telaCarregamento.setBackground(new Color(40, 40, 40));
                            JLabel lblCarregando = new JLabel("Carregando...", SwingConstants.CENTER);
                            lblCarregando.setForeground(Color.WHITE);
                            lblCarregando.setFont(new Font("Arial", Font.BOLD, 24));
                            telaCarregamento.add(lblCarregando, BorderLayout.CENTER);
                            parentPanel.add(telaCarregamento, "TelaCarregamento");
                            cardLayout.show(parentPanel, "TelaCarregamento");

                            new Thread(() -> {
                                TelaResultado telaEvolucao = new TelaResultado(evolucao, parentPanel, cardLayout);
                                parentPanel.add(telaEvolucao, "TelaResultado");
                                cardLayout.show(parentPanel, "TelaResultado");
                            }).start();
                        }
                    });
                    painelEvolucoes.add(btnEvolucao);
                }
            }

            JButton btnVoltar = new JButton("Voltar");
            btnVoltar.setBackground(Color.RED);
            btnVoltar.setForeground(Color.WHITE);
            btnVoltar.setFocusPainted(false);
            btnVoltar.setBorderPainted(false);
            btnVoltar.setAlignmentX(Component.LEFT_ALIGNMENT);
            btnVoltar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(parentPanel, "TelaInicial");
                }
            });

            panel.add(Box.createVerticalStrut(10));
            panel.add(lblNome);
            panel.add(Box.createVerticalStrut(5));
            panel.add(lblAltura);
            panel.add(Box.createVerticalStrut(5));
            panel.add(lblPeso);
            panel.add(Box.createVerticalStrut(10));
            panel.add(lblImagem);
            panel.add(Box.createVerticalStrut(5)); 
            panel.add(lblTipos);
            panel.add(painelTipos);
            panel.add(Box.createVerticalStrut(5)); 
            panel.add(lblFraquezas);
            panel.add(painelFraquezas);
            panel.add(Box.createVerticalStrut(5));
            panel.add(lblResistencias);
            panel.add(painelResistencias);
            panel.add(Box.createVerticalStrut(5)); 
            panel.add(lblImunidades);
            panel.add(painelImunidades);
            panel.add(Box.createVerticalStrut(5)); 
            panel.add(lblStatus);
            panel.add(painelStatus);
            panel.add(Box.createVerticalStrut(5)); 
            panel.add(lblDescricao);
            panel.add(txtDescricao);
            panel.add(Box.createVerticalStrut(5)); 
            panel.add(lblHabilidades);
            panel.add(painelHabilidades);
            panel.add(Box.createVerticalStrut(5)); 
            panel.add(lblEvolucoes);
            panel.add(painelEvolucoes);
            panel.add(Box.createVerticalStrut(10));
            panel.add(btnVoltar);
            panel.add(Box.createVerticalStrut(10));

            add(panel, BorderLayout.CENTER);
        } // No método TelaResultado, substitua a parte do "else" por isso:
        else {
            JPanel panelErro = new JPanel(new BorderLayout());
            panelErro.setBackground(new Color(40, 40, 40));
            panelErro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona margem

            JLabel lblErro = new JLabel("Pokémon não encontrado.");
            lblErro.setForeground(Color.WHITE);
            lblErro.setHorizontalAlignment(SwingConstants.CENTER);
            panelErro.add(lblErro, BorderLayout.CENTER);

            JButton btnVoltarErro = new JButton("Voltar");
            btnVoltarErro.setBackground(Color.RED);
            btnVoltarErro.setForeground(Color.WHITE);
            btnVoltarErro.setFocusPainted(false);
            btnVoltarErro.setBorderPainted(false);
            btnVoltarErro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(parentPanel, "TelaInicial");
                }
            });

            JPanel painelBotao = new JPanel();
            painelBotao.setBackground(new Color(40, 40, 40));
            painelBotao.add(btnVoltarErro);
            panelErro.add(painelBotao, BorderLayout.SOUTH);

            add(panelErro, BorderLayout.CENTER);
        }
    }

    private void definirCoresTipos() {
        coresTipos = new HashMap<>();
        coresTipos.put("BUG", new Color(166, 185, 26));
        coresTipos.put("DARK", new Color(112, 87, 70));
        coresTipos.put("DRAGON", new Color(111, 53, 252));
        coresTipos.put("ELECTRIC", new Color(247, 208, 44));
        coresTipos.put("FAIRY", new Color(214, 133, 173));
        coresTipos.put("FIGHTING", new Color(194, 46, 40));
        coresTipos.put("FIRE", new Color(238, 129, 48));
        coresTipos.put("FLYING", new Color(169, 143, 243));
        coresTipos.put("GHOST", new Color(115, 87, 151));
        coresTipos.put("GRASS", new Color(122, 199, 76));
        coresTipos.put("GROUND", new Color(226, 191, 101));
        coresTipos.put("ICE", new Color(150, 217, 214));
        coresTipos.put("NORMAL", new Color(168, 167, 122));
        coresTipos.put("POISON", new Color(163, 62, 161));
        coresTipos.put("PSYCHIC", new Color(249, 85, 135));
        coresTipos.put("ROCK", new Color(182, 161, 54));
        coresTipos.put("STEEL", new Color(183, 183, 206));
        coresTipos.put("WATER", new Color(99, 144, 240));
    }

    private Map<String, Integer> contarRepeticoes(List<String> lista) {
        Map<String, Integer> contagem = new HashMap<>();
        for (String item : lista) {
            contagem.put(item, contagem.getOrDefault(item, 0) + 1);
        }
        return contagem;
    }
}