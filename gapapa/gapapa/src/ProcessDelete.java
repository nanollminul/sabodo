import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ProcessDelete extends JFrame {
    Statement statement;
    ResultSet resultSet;
    JButton bDelete, bBack;
    JLabel lTitle, lNoTelp;
    JTextField fNoTelp;

    public void ProcessDelete() {
        this.lTitle = new JLabel("Hapus Kontak Telepon");
        this.lTitle.setForeground(new Color(150, 75, 0));
        this.lNoTelp = new JLabel("No. Telp ");
        this.lNoTelp.setForeground(new Color(150, 75, 0));
        this.bDelete = new JButton("Hapus");
        this.bDelete.setBackground(new Color(150, 75, 0));
        this.bBack = new JButton("Kembali");
        this.bBack.setBackground(new Color(150, 75, 0));
        this.fNoTelp = new JTextField();
        this.fNoTelp.setBackground(new Color(150, 75, 0));

        this.setTitle("Hapus Kontak Telepon");
        this.setSize(300, 200);
        this.lTitle.setHorizontalAlignment(0);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setLayout((LayoutManager)null);

        this.add(this.lTitle);
        this.add(this.lNoTelp);
        this.add(this.fNoTelp);
        this.add(this.bDelete);
        this.add(this.bBack);

        getContentPane().setBackground(new Color(227, 202, 175));

        this.lTitle.setBounds(50, 10, 150, 25);
        this.lNoTelp.setBounds(50, 50, 100, 25);
        this.fNoTelp.setBounds(100, 50, 100, 25);
        this.bBack.setBounds(30, 90, 100, 25);
        this.bDelete.setBounds(140, 90, 100, 25);
        this.bBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ProcessDelete.this.setVisible(false);
                new Menu();
            }
        });
        this.bDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ProcessDelete.this.btnDelactionListener();
                new ReadData();
            }
        });
    }

    private void btnDelactionListener() {
        DBConnection koneksi = new DBConnection();
        try {
            this.statement = koneksi.getConnection().createStatement();
            this.statement.executeUpdate("DELETE FROM tb_mahasiswa WHERE no_telp='" + this.fNoTelp.getText() + "'");
            JOptionPane.showMessageDialog((Component)null, "Data berhasil di Hapus!", "Hasil", 1);
            this.statement.close();
        } catch (SQLException var3) {
            JOptionPane.showMessageDialog((Component)null, "Data Gagal di Hapus!", "Hasil", 0);
        } catch (ClassNotFoundException var4) {
            JOptionPane.showMessageDialog((Component)null, "Driver Tidak Ditemukan!", "Hasil", 0);
        }

    }
}