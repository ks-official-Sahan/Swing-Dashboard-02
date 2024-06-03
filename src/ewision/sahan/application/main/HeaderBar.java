package ewision.sahan.application.main;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ks.official.sahan
 */
public class HeaderBar extends JPanel {

    private JLabel sessionLabel;
    private Timer timer;
    private int sessionTime = 3600; // session time in seconds (e.g., 1 hour)
    private JLabel logoLabel;

    public void setLogoLabel(String text, Icon icon) {
        logoLabel.setText(text);
        logoLabel.setIcon(icon);
    }

    public HeaderBar() {
        setBorder(new EmptyBorder(2, 15, 2, 15));
        
        setLayout(new MigLayout("insets 0, fillx, aligny center",
                "[fill]10[center]10[fill]",
                "[]"));

        // Set the header bar style
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Header.background;"
                + "arc:20;");

        ImageIcon icon = new ImageIcon(getClass().getResource("/ewision/sahan/icon/png/logo.png"));
        Image image = (Image) icon.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        // Logo and text on the left
        logoLabel = new JLabel();
        logoLabel.setIcon(icon);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(logoLabel, "left");

        // Session countdown timer in the center
        sessionLabel = new JLabel();
        sessionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(sessionLabel, "center");

        // User profile icon on the right
        JLabel userProfileIcon = new JLabel();
        userProfileIcon.setIcon(icon);
        userProfileIcon.setHorizontalAlignment(SwingConstants.TRAILING);
        //userProfileIcon.setSize(100, 50);
        add(userProfileIcon, "right");

        // Initialize the session timer
        startSessionTimer();
    }

    private void startSessionTimer() {
        updateSessionLabel();
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sessionTime > 0) {
                    sessionTime--;
                    updateSessionLabel();
                } else {
                    timer.stop();
                    // Handle session timeout here if needed
                }
            }
        });
        timer.start();
    }

    private void updateSessionLabel() {
        int minutes = sessionTime / 60;
        int seconds = sessionTime % 60;
        sessionLabel.setText(String.format("Session Time: %02d:%02d", minutes, seconds));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Application Header Bar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        HeaderBar headerBar = new HeaderBar();
        frame.add(headerBar, BorderLayout.NORTH);

        frame.setVisible(true);
    }
}
