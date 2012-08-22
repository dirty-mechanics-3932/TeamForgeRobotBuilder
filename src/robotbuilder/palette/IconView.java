/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robotbuilder.palette;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import robotbuilder.Utils;
import robotbuilder.data.PaletteComponent;

/**
 *
 * @author alex
 */
public class IconView extends JPanel {
    Palette palette;
    
    public IconView(Palette palette) {
        this.palette = palette;
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        TreeModel model = palette.getPaletteModel();
        Enumeration children = ((DefaultMutableTreeNode) model.getRoot()).children();
        while (children.hasMoreElements()) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
            add(new IconSection(child));
        }
    }

    private static class IconSection extends JPanel {
        private IconSection(DefaultMutableTreeNode node) {
            //BorderFactory.createT
            setBorder(BorderFactory.createTitledBorder(node.getUserObject().toString()));
            TitledBorder border = ((TitledBorder) getBorder());
            border.setTitleFont(border.getTitleFont().deriveFont(Font.BOLD));

            setLayout(new GridLayout(0, 2, 5, 5));
            
            Enumeration children = node.children();
            while (children.hasMoreElements()) {
                PaletteComponent component = (PaletteComponent) ((DefaultMutableTreeNode) children.nextElement()).getUserObject();
                add(new PaletteIcon(component));
            }
        }
    }
    
    private static class PaletteIcon extends JLabel {
        private PaletteIcon(PaletteComponent component) {
            setIcon(new ImageIcon(Utils.getResource("/icons/"+component.getName()+".png")));
        }
    }
}
