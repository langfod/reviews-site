package com.dihelix.langfod.reviewssite;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReviewRepositoryTest {

	ReviewRepository underTest;
	Review testReview;
	Collection<Review> testReviewList;
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
		underTest = new ReviewRepository();
		testReview = new Review(testId, testTitle, testCategory, testContent, testBookPublishedDate, testDescription,
				testImageUrl, testTags);
		testReviewList = new SampleReviewFactory().sampleReviewList();
	}

	@Test
	public void shouldAddSingleReviewToMap() {
		assertThat(underTest.reviews.size(), is(0));
		underTest.addReview(testReview);
		assertThat(underTest.reviews.size(), is(1));
	}

	@Test
	public void shouldAddCollectionOfReviewsToMap() {
		assertThat(underTest.reviews.size(), is(0));
		underTest.addReviews(testReviewList);
		assertThat(underTest.reviews.size(), is(testReviewList.size()));
	}

	@Test
	public void overloadedConstructorShouldTakeCollectionOfReviewsToMap() {
		underTest = new ReviewRepository(testReviewList);
		assertThat(underTest.reviews.size(), is(testReviewList.size()));
	}

	@Test
	public void findAllShouldReturnCollectionOfReviews() {
		underTest.addReviews(testReviewList);
		assertThat(underTest.findAll().size(), is(testReviewList.size()));
	}

	@Test
	public void findAllTagsShouldReturnAUniqueListOfAllTags() {
		underTest.addReview(testReview);

		testReview = new Review(43L, testTitle, testCategory, testContent, testBookPublishedDate, testDescription,
				testImageUrl, testTags);
		underTest.addReview(testReview);

		testReview = new Review(44L, testTitle, testCategory, testContent, testBookPublishedDate, testDescription,
				testImageUrl, Arrays.asList("tag10", "tag2", "tag3"));
		underTest.addReview(testReview);

		assertThat(underTest.findAllTags(), containsInAnyOrder("tag1", "tag2", "tag3", "tag10"));
	}

	@Test
	public void findByTagShouldReturnACollectionOFReviewWithThatTag() {
		underTest.addReview(testReview);

		testReview = new Review(43L, testTitle, testCategory, testContent, testBookPublishedDate, testDescription,
				testImageUrl, testTags);
		underTest.addReview(testReview);

		testReview = new Review(44L, testTitle, testCategory, testContent, testBookPublishedDate, testDescription,
				testImageUrl, Arrays.asList("tag10", "tag2", "tag3"));
		underTest.addReview(testReview);

		assertThat(underTest.findByTag("tag10").size(), is(1));
		assertThat(underTest.findByTag("tag3").size(), is(3));

	}

	@Test
	public void findOneReturnReviewWithSuppliedId() {
		underTest.addReview(testReview);

		Review testReview43 = new Review(43L, testTitle, testCategory, testContent, testBookPublishedDate,
				testDescription, testImageUrl, testTags);
		underTest.addReview(testReview43);

		Review testReview44 = new Review(44L, testTitle, testCategory, testContent, testBookPublishedDate,
				testDescription, testImageUrl, Arrays.asList("tag10", "tag2", "tag3"));
		underTest.addReview(testReview44);

		assertThat(underTest.findOne(44L), is(testReview44));
		assertThat(underTest.findOne(43L), is(testReview43));
		assertThat(underTest.findOne(42L), is(testReview));

	}

}
