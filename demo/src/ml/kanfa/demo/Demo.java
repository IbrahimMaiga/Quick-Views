package ml.kanfa.demo;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import ml.kanfa.qv.swing.*;
import ml.kanfa.qv.swing.Icon;
import ml.kanfa.qv.swing.components.alert.InfoAlert;
import ml.kanfa.qv.swing.components.larrv.Effect;
import ml.kanfa.qv.swing.components.larrv.ElementColor;
import ml.kanfa.qv.swing.components.larrv.LarrStyle;
import ml.kanfa.qv.swing.components.larrv.LarrElement;
import ml.kanfa.qv.swing.components.larrv.LarrView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class Demo
 *
 * @author Ibrahim Maïga.
 */
public class Demo {

    /**
     * Method main
     * @param args command line arguments
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(
                () -> {
                    try {
                        UIManager.setLookAndFeel(new WindowsLookAndFeel());
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                    createFrame();
                }
        );
    }

    private static void createFrame(){
        JFrame frame = new JFrame("LarrView Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        ArrayList<LarrElement> larrElements = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            larrElements.add(new LarrElement("Panel Title " + i, new JLabel("View " + i)));
        }
        LarrStyle style = new LarrStyle(
                new Round(20, 20),
                new Effect(
                        new ElementColor(Color.black, Color.WHITE, Color.WHITE),
                        new ElementColor(Color.DARK_GRAY, Color.LIGHT_GRAY, Color.ORANGE),
                        new ElementColor(Color.CYAN, Color.BLACK, Color.MAGENTA)
                ),
                new Icon()
        );

        //LarrView larrView = new LarrView(larrElements, style, true);
        LarrView larrView = new LarrView(larrElements, true);
        JPanel panel =  new JPanel();
        panel.add(new Label(String.valueOf(larrView.getLastIndex())));

        JButton add = new JButton("Ajouter");
        JButton active = new JButton("Activer");
        JButton disable = new JButton("Desactiver");
        JButton selected = new JButton("Selectionner");
        JButton remove = new JButton("Supprimer");
        JButton removeAll = new JButton("Tout Supprimer");
        JButton change = new JButton("Changer l'icone du champs recherche");
        JButton alert = new JButton("Afficher une Alerte");
        add.setToolTipText("Ajouter un nouveau Larrview dans la liste");
        active.setToolTipText("Activer le champs de saisie.");
        disable.setToolTipText("Désactiver le champs de saisie.");
        selected.setToolTipText("Afficher le nom du LarrView Selectionné");
        remove.setToolTipText("Supprimer le LarrView");
        removeAll.setToolTipText("Supprimer tout les LarrView du conteneur");
        change.setToolTipText("Changer l'icone du champ de saisie.");
        alert.setToolTipText("Afficher une alerte de type Information");
        add.addActionListener(e -> {
            int index = larrView.getLastIndex();
            larrView.addLarrComponent(new LarrElement("Other Panel Title " + index,
                                                      new JLabel("View " + index)));
        });

        active.addActionListener(
                e -> larrView.activateSearch("")
        );

        disable.addActionListener(
                e -> larrView.deactivateSearch()
        );

        selected.addActionListener(
                e -> {
                    if (larrView.getSelected() != null){
                        JOptionPane.showMessageDialog(null, larrView.getSelected().getTitle());
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Aucune Selection");
                    }
                }
        );

        remove.addActionListener(
                e -> {
                    if (larrView.getSelected() != null){
                        int response = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer cet element ?",
                                                                     "Demande de supression", JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION){
                            larrView.removeSelected();
                        }
                    }
                }
        );

        removeAll.addActionListener(
                e -> {
                    int response = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir tout supprimer ?",
                                                                 "Demande de supression", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION){
                        larrView.removeLarrComponents();
                    }
                }
        );
        change.addActionListener(
                e -> larrView.setSearchIcon("other_search_icon.png")
        );
        alert.addActionListener(e -> {
            JLabel label = new JLabel("Kanfa");
            label.setBackground(Color.GREEN);
            new InfoAlert(100, 100, label);
        });
        JPanel pan = new JPanel();
        addAll(pan, add, active, disable, selected, remove,removeAll, change, alert);
        frame.getContentPane().add(larrView, BorderLayout.CENTER);
        frame.getContentPane().add(pan, BorderLayout.NORTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addAll(JPanel panel, JComponent... components){
        for (JComponent component : components){
            panel.add(component);
        }
    }
}
