package by.tms.instaclone.dto;
import java.util.List;

/**
 * Класс описывает содерижимое контента для страницы HOME_PAGE
 */
public class UserHomePageDto {
    private String name;
    private String username;
    private List<PublisherCardLastPostDto> publishersCards;

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPublishersCards(List<PublisherCardLastPostDto> list) {
        this.publishersCards = list;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public List<PublisherCardLastPostDto> getPublishersCards() {
        return publishersCards;
    }
}