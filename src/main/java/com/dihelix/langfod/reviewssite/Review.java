package com.dihelix.langfod.reviewssite;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;

public class Review {

	private String category;
	private String content;
	private LocalDate bookPublishedDate;
	private String description;
	private Long id;
	private String imageUrl;
	private Collection<String> tags;
	private String title;

	public Review(long id, String title, String category, String content, LocalDate bookPublishedDate,
			String description, String imageUrl, Collection<String> tags) {
		this.id = id;
		this.imageUrl = imageUrl;
		this.category = category;
		this.content = content;
		this.bookPublishedDate = bookPublishedDate;
		this.description = description;
		this.tags = tags;
		this.title = title;
	}

	public Review(Long id, String title, String category, String content, LocalDate bookPublishedDate,
			String description, URL imageUrl, Collection<String> tags) {
		this(id, title, category, content, bookPublishedDate, description, imageUrl.toString(), tags);
	}

	public String getCategory() {
		return category;
	}

	public String getContent() {
		return content;
	}

	public LocalDate getBookPublishedDate() {
		return bookPublishedDate;
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Collection<String> getTags() {
		return tags;
	}

	public String getTitle() {
		return title;
	}

}
