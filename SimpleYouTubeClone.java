import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Video {
    private String title;
    private String uploader;
    private int views;

    public Video(String title, String uploader, int views) {
        this.title = title;
        this.uploader = uploader;
        this.views = views;
    }

    public String getTitle() { return title; }
    public String getUploader() { return uploader; }
    public int getViews() { return views; }

    @Override
    public String toString() {
        return title + " by " + uploader + " (" + views + " views)";
    }
}

public class SimpleYouTubeClone extends JFrame {
    private JTextField searchField = new JTextField(20);
    private DefaultListModel<Video> videoListModel = new DefaultListModel<>();
    private JList<Video> videoList = new JList<>(videoListModel);
    private JTextArea videoPlayerArea = new JTextArea(8, 30);

    private List<Video> allVideos = new ArrayList<>();

    public SimpleYouTubeClone() {
        super("Simple YouTube Clone");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Sample videos
        allVideos.add(new Video("Java Tutorial for Beginners", "CodeAcademy", 12345));
        allVideos.add(new Video("Top 10 Programming Languages", "TechReview", 9876));
        allVideos.add(new Video("Funny Cats Compilation", "CatLover", 54321));
        allVideos.add(new Video("Music Video - New Hit", "MusicChannel", 150000));
        allVideos.add(new Video("How to Cook Pasta", "Foodie", 2345));

        videoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        updateVideoList(allVideos);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search Videos:"));
        topPanel.add(searchField);
        JButton searchBtn = new JButton("Search");
        topPanel.add(searchBtn);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Videos:"), BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(videoList), BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("Video Player:"), BorderLayout.NORTH);
        videoPlayerArea.setEditable(false);
        videoPlayerArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        rightPanel.add(new JScrollPane(videoPlayerArea), BorderLayout.CENTER);

        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        searchBtn.addActionListener(e -> searchVideos());
        videoList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                playVideo(videoList.getSelectedValue());
            }
        });
    }

    private void updateVideoList(List<Video> videos) {
        videoListModel.clear();
        for (Video v : videos) {
            videoListModel.addElement(v);
        }
    }

    private void searchVideos() {
        String query = searchField.getText().trim().toLowerCase();
        List<Video> filtered = allVideos.stream()
                .filter(v -> v.getTitle().toLowerCase().contains(query) || v.getUploader().toLowerCase().contains(query))
                .collect(Collectors.toList());
        updateVideoList(filtered);
        videoPlayerArea.setText("");
    }

    private void playVideo(Video video) {
        if (video == null) {
            videoPlayerArea.setText("");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Now Playing:\n\n");
        sb.append("Title: ").append(video.getTitle()).append("\n");
        sb.append("Uploader: ").append(video.getUploader()).append("\n");
        sb.append("Views: ").append(video.getViews()).append("\n\n");
        sb.append("<< Video playback simulation >>\n");
        sb.append("Imagine a cool video playing here...");
        videoPlayerArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleYouTubeClone().setVisible(true));
    }
}
