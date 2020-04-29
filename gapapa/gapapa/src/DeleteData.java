import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteData extends JFrame {
    Statement statement;
    ResultSet resultSet;
    String[][] datas = new String[500][4];
    String[] kolom = {"NAMA", "NO TELP"};
    JLabel lTitle;
    JButton bDelete, bBack;
    JTable tTable;
    JScrollPane scrollPane;

    public DeleteData() throws SQLException, ClassNotFoundException {
        lTitle = new JLabel ("Seluruh Kontak Telepon");
        lTitle.setFont(new Font("Regular", Font.BOLD, 18));
        lTitle.setForeground(new Color(150, 75, 0));
        bDelete = new JButton ("Delete");
        bDelete.setFont(new Font("Regular", Font.PLAIN, 14));
        bDelete.setBackground(new Color(150, 75, 0));
        bBack = new JButton ("Kembali");
        bBack.setFont(new Font("Regular", Font.PLAIN, 14));
        bBack.setBackground(new Color(150, 75, 0));
        tTable = new JTable(datas, kolom);
        tTable.setBackground(new Color(150, 75, 0));
        scrollPane = new JScrollPane(tTable);
        scrollPane.setBackground(new Color(150, 75, 0));

        getContentPane().setBackground(new Color(227, 202, 175));

        setTitle("Hapus Kontak Telepon");
        DBConnection connec = new DBConnection();
        statement = connec.getConnection().createStatement();
        String sql = "SELECT * FROM tb_mahasiswa";
        resultSet = statement.executeQuery(sql);
        int row = 0;
        while (resultSet.next()){
            datas[row][0] = resultSet.getString("nama");
            datas[row][1] = resultSet.getString("no_telp");
            row++;
        }
        setLayout(null);
        add(lTitle);
        add(bDelete);
        add(bBack);
        add(scrollPane);

        lTitle.setBounds(160, 30, 300, 30);
        scrollPane.setBounds(70, 70, 400, 400);
        bDelete.setBounds(170, 490, 90, 25);
        bBack.setBounds(280, 490, 90, 25);

        setSize(550,650);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Main();
            }
        });

        bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProcessDelete hapus = new ProcessDelete();
                hapus.ProcessDelete();
                setVisible(false);
            }
        });
    }
}
