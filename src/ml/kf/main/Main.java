package ml.kf.main;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import ml.kf.qv.swing.*;
import ml.kf.qv.swing.Icon;
import ml.kf.qv.swing.larrv.models.Effect;
import ml.kf.qv.swing.larrv.models.ElementColor;
import ml.kf.qv.swing.larrv.models.LarrStyle;
import ml.kf.qv.swing.larrv.view.LarrElement;
import ml.kf.qv.swing.larrv.view.LarrView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Ibrahim Maïga.
 *
 * CLass Main
 */
public class Main {

    /**
     * Method main
     * @param args command line arguments
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(
                () ->{
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
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
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
                        System.out.println(larrView.getSelected().getTitle());
                    }
                    else{
                        System.out.println("Aucune selection");
                    }
                }
        );

        remove.addActionListener(
                e -> {
                    if (larrView.getSelected() != null){
                        int response = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir suppprimer cet element ?",
                                                                     "Demande de supression", JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION){
                            larrView.removeSelected();
                        }
                    }
                }
        );

        removeAll.addActionListener(
                e -> {
                    int response = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir tout suppprimer ?",
                                                                 "Demande de supression", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION){
                        larrView.removeLarrComponents();
                    }
                }
        );

        change.addActionListener(
                e -> larrView.setSearchIcon("other_search_icon.png")
        );

        JPanel pan = new JPanel();
        addAll(pan, add, active, disable, selected, remove,removeAll, change);
        frame.getContentPane().add(larrView, BorderLayout.CENTER);
        frame.getContentPane().add(pan, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private static void addAll(JPanel panel, JComponent... components){
        for (JComponent component : components){
            panel.add(component);
        }
    }
}
