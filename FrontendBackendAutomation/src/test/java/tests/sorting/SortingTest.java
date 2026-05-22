package tests.sorting;

import com.microsoft.playwright.Page;
import org.testng.annotations.Test;
import constants.SortingConstants;
import factories.DriverFactory;
import pages.SortingPage;
import utils.AssertionUtils;
import utils.HandleLoginFailure;
import utils.WaitUtils;

public class SortingTest {

    @Test
    public void testSortNewest() {
        Page page = HandleLoginFailure.setupAndNavigate();
        SortingPage sortingPage = new SortingPage(page);

        sortingPage.selectSortOption(SortingConstants.SORT_NEWEST);
        WaitUtils.mediumPause(page);
        AssertionUtils.assertSortApplied(sortingPage, SortingConstants.SORT_NEWEST);

        DriverFactory.closeDriver();
    }

    @Test
    public void testSortPopular() {
        Page page = HandleLoginFailure.setupAndNavigate();
        SortingPage sortingPage = new SortingPage(page);

        sortingPage.selectSortOption(SortingConstants.SORT_POPULAR);
        WaitUtils.mediumPause(page);
        AssertionUtils.assertSortApplied(sortingPage, SortingConstants.SORT_POPULAR);

        DriverFactory.closeDriver();
    }

    @Test
    public void testSortPriceAsc() {
        Page page = HandleLoginFailure.setupAndNavigate();
        SortingPage sortingPage = new SortingPage(page);

        sortingPage.selectSortOption(SortingConstants.SORT_PRICE_ASC);
        WaitUtils.mediumPause(page);
        AssertionUtils.assertSortApplied(sortingPage, SortingConstants.SORT_PRICE_ASC);

        DriverFactory.closeDriver();
    }

    @Test
    public void testSortPriceDesc() {
        Page page = HandleLoginFailure.setupAndNavigate();
        SortingPage sortingPage = new SortingPage(page);

        sortingPage.selectSortOption(SortingConstants.SORT_PRICE_DESC);
        WaitUtils.mediumPause(page);
        AssertionUtils.assertSortApplied(sortingPage, SortingConstants.SORT_PRICE_DESC);

        DriverFactory.closeDriver();
    }

    @Test
    public void testSortRating() {
        Page page = HandleLoginFailure.setupAndNavigate();
        SortingPage sortingPage = new SortingPage(page);

        sortingPage.selectSortOption(SortingConstants.SORT_RATING);
        WaitUtils.mediumPause(page);
        AssertionUtils.assertSortApplied(sortingPage, SortingConstants.SORT_RATING);

        DriverFactory.closeDriver();
    }
}
