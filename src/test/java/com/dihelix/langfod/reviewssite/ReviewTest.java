package com.dihelix.langfod.reviewssite;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReviewTest {

	private Review reviewUnderTest;
	private final LocalDate testBookPublishedDate = LocalDate.of(1995, Month.MAY, 23);
	private final String testCategory = "Category";
	private final String testContent = "Content";
	private final String testDescription = "Description";
	private final Long testId = 42L;
	private final String testImageUrl = "Title";
	private final List<String> testTags = Arrays.asList("tag1", "tag2", "tag3");
	private final String testTitle = "Title";

	@Before
	public void setUp() {
		reviewUnderTest = new Review(testId, testTitle, testCategory, testContent, testBookPublishedDate, testDescription,
				testImageUrl, testTags);
	}

	@Test
	public void getBookPublishedDateShouldReturnTestValue() {
		assertThat(reviewUnderTest.getBookPublishedDate(), is(testBookPublishedDate));
	}

	@Test
	public void getCategoryShouldReturnTestValue() {
		assertThat(reviewUnderTest.getCategory(), is(testCategory));
	}

	@Test
	public void getContentShouldReturnTestValue() {
		assertThat(reviewUnderTest.getContent(), is(testContent));
	}

	@Test
	public void getDescriptionShouldReturnTestValue() {
		assertThat(reviewUnderTest.getDescription(), is(testDescription));
	}

	@Test
	public void getIdShouldReturnTestValue() {
		assertThat(reviewUnderTest.getId(), is(testId));
	}

	@Test
	public void getImageUrlShouldReturnTestValue() {
		assertThat(reviewUnderTest.getImageUrl(), is(testImageUrl));
	}

	@Test
	public void getTagsShouldReturnTestValue() {
		assertThat(reviewUnderTest.getTags(), containsInAnyOrder("tag1", "tag2", "tag3"));
	}

	@Test
	public void getTitleShouldReturnTestValue() {
		assertThat(reviewUnderTest.getTitle(), is(testTitle));
	}

	@Test
	public void constructorShouldAlsoAcceptURL() {
		final String urlString = "http://www.example.com/foobar/";

		try {
			URL url = new URL(urlString);
			reviewUnderTest = new Review(42L, testTitle, testCategory, testContent, testBookPublishedDate,
					testDescription, url, testTags);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		assertThat(reviewUnderTest.getImageUrl(), is(urlString));

	}
}
