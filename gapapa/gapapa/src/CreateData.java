import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class CreateData extends JFrame {
    JLabel lTitle, lNama, lNoTelp;
    JTextField fNama, fNoTelp;
    JButton bSave, bBack;
    Statement statement;
    String nama, alamat, no_telp;
    int nim;

    public CreateData(){
        setTitle("INPUT Kontak Telepon");
        lTitle = new JLabel("Input Kontak Telepon");
        lTitle.setFont(new Font("Regular", Font.BOLD, 18));
        lTitle.setForeground(new Color(150, 75, 0));
        lNama = new JLabel("Nama ");
        lNama.setFont(new Font("Regular", Font.PLAIN, 18));
        lNama.setForeground(new Color(150, 75, 0));
        fNama = new JTextField();
        fNama.setFont(new Font("Regular", Font.PLAIN, 16));
        fNama.setForeground(new Color(245, 249, 250));
        fNama.setBackground(new Color(150, 75, 0));
        lNoTelp = new JLabel("No Telp ");
        lNoTelp.setFont(new Font("Regular", Font.PLAIN, 18));
        lNoTelp.setForeground(new Color(150, 75, 0));
        fNoTelp = new JTextField();
        fNoTelp.setFont(new Font("Regular", Font.PLAIN, 16));
        fNoTelp.setForeground(new Color(245, 249, 250));
        fNoTelp.setBackground(new Color(150, 75, 0));

        bSave = new JButton("Simpan");
        bSave.setFont(new Font("Regular",Font.PLAIN, 14));
        bSave.setBackground(new Color(150, 75, 0));
        bBack = new JButton("Kembali");
        bBack.setFont(new Font("Regular",Font.PLAIN, 14));
        bBack.setBackground(new Color(150, 75, 0));

        getContentPane().setBackground(new Color(227, 202, 175));

        setLayout(null);
        add(lTitle);
        add(lNama);
        add(fNama);
        add(lNoTelp);
        add(fNoTelp);
        add(bSave);
        add(bBack);

        lTitle.setBounds(110, 20, 250, 30);
        lNama.setBounds(30, 70, 120, 30);
        fNama.setBounds(110, 73,270,25);
        lNoTelp.setBounds(30, 110, 120, 30);
        fNoTelp.setBounds(110, 113, 270, 25);
        bSave.setBounds(120, 150, 90, 30);
        bBack.setBounds(220, 150, 90,30);

        setSize(440, 350);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        bSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    nama = fNama.getText();
                    no_telp = fNoTelp.getText();

                    Data data = new Data(nama, no_telp);

                    DBConnection connec = new DBConnection();
                    try {
                        statement = connec.getConnection().createStatement();
                        statement.executeUpdate("INSERT INTO tb_mahasiswa VALUES('" + data.getNama() + "','" + data.getNo_telp() + "')");
                        JOptionPane.showMessageDialog(rootPane, "Data Berhasil Disimpan");
                    } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(CreateData.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(CreateData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Tipe Data Salah");
                }catch (Error ext){
                    JOptionPane.showMessageDialog(rootPane, "SALAH!!");
                }

                setVisible(false);
            }
        });

        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Main();
            }
        });
    }
}
