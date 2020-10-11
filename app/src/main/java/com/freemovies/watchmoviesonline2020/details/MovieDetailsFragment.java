package com.freemovies.watchmoviesonline2020.details;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.freemovies.watchmoviesonline2020.Api;
import com.freemovies.watchmoviesonline2020.BaseApplication;
import com.freemovies.watchmoviesonline2020.Constants;
import com.freemovies.watchmoviesonline2020.Movie;
import com.freemovies.watchmoviesonline2020.R;
import com.freemovies.watchmoviesonline2020.Review;
import com.freemovies.watchmoviesonline2020.Video;
import com.freemovies.watchmoviesonline2020.WatchMovieActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MovieDetailsFragment extends Fragment implements MovieDetailsView, View.OnClickListener {
    @Inject
    MovieDetailsPresenter movieDetailsPresenter;
    String movieName, myMoviename;

    @BindView(R.id.movie_poster)
    ImageView poster;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.movie_name)
    TextView title;
    @BindView(R.id.movie_year)
    TextView releaseDate;
    @BindView(R.id.movie_rating)
    TextView rating;
    @BindView(R.id.movie_description)
    TextView overview;
    @BindView(R.id.trailers_label)
    TextView label;
    @BindView(R.id.trailers)
    LinearLayout trailers;
    @BindView(R.id.trailers_container)
    HorizontalScrollView horizontalScrollView;
    @BindView(R.id.reviews_label)
    TextView reviews;
    @BindView(R.id.reviews)
    LinearLayout reviewsContainer;
    @BindView(R.id.favorite)
    FloatingActionButton favorite;
    @BindView(R.id.toolbar)
    @Nullable
    Toolbar toolbar;

    private Movie movie;
    private Unbinder unbinder;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    public static MovieDetailsFragment getInstance(@NonNull Movie movie) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.MOVIE, movie);
        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(args);
        return movieDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BaseApplication) getActivity().getApplication()).createDetailsComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setToolbar();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            Movie movie = (Movie) getArguments().get(Constants.MOVIE);
            if (movie != null) {
                this.movie = movie;
                movieDetailsPresenter.setView(this);
                movieDetailsPresenter.showDetails((movie));
                movieDetailsPresenter.showFavoriteButton(movie);
            }
        }
    }

    private void setToolbar() {
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        collapsingToolbar.setTitle(getString(R.string.movie_details));
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbar.setTitleEnabled(true);

        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        } else {
            // Don't inflate. Tablet is in landscape mode.
        }
    }

    @Override
    public void showDetails(Movie movie) {
        Glide.with(getContext()).load(Api.getBackdropPath(movie.getBackdropPath())).into(poster);
        title.setText(movie.getTitle());
        movieName = movie.getTitle();
        releaseDate.setText(String.format(getString(R.string.release_date), movie.getReleaseDate()));
        rating.setText(String.format(getString(R.string.rating), String.valueOf(movie.getVoteAverage())));
        overview.setText(movie.getOverview());
        movieDetailsPresenter.showTrailers(movie);
        movieDetailsPresenter.showReviews(movie);
    }

    @Override
    public void showTrailers(List<Video> trailers) {

//        AdView bigbanner = (AdView) getView().findViewById(R.id.bigbanner);
//        bigbanner.getLayoutParams().height = AdSize.LARGE_BANNER.getHeightInPixels(getView().getContext());
//        bigbanner.loadAd(new AdRequest.Builder().build());

        AdView secondbig = (AdView) getView().findViewById(R.id.secondbig);
        secondbig.getLayoutParams().height = AdSize.LARGE_BANNER.getHeightInPixels(getView().getContext());
        secondbig.loadAd(new AdRequest.Builder().build());
//
//        AdView medium = (AdView) getView().findViewById(R.id.trailersandrevies_medium);
//        medium.getLayoutParams().height = AdSize.MEDIUM_RECTANGLE.getHeightInPixels(getView().getContext());
//        medium.loadAd(new AdRequest.Builder().build());

        AdView medium2 = (AdView) getView().findViewById(R.id.fragmentmoviesdetails_medium2);
        medium2.getLayoutParams().height = AdSize.MEDIUM_RECTANGLE.getHeightInPixels(getView().getContext());
        medium2.loadAd(new AdRequest.Builder().build());

//        Toast.makeText(getView().getContext(), ""+movieName, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getView().getContext(), WatchMovieActivity.class);

            Button source1 = getView().findViewById(R.id.sourece1);
            source1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO Auto-generated method stub

//                    Intent intent = new Intent(getView().getContext(), WatchMovieActivity.class);
                    myMoviename = movieName;
                    if ((myMoviename.contains(":")) || (myMoviename.contains("(")) || (myMoviename.contains(".")) || (myMoviename.contains(",")) || (myMoviename.contains("-"))) {
                        int iend = myMoviename.indexOf(":");
                        if ((myMoviename.contains("("))) iend = myMoviename.indexOf("(") - 1;
                        if ((myMoviename.contains("."))) iend = myMoviename.indexOf(".");

                        if (iend != -1) {
                            myMoviename = myMoviename.substring(0, iend);
                            myMoviename = myMoviename.replace(" ", "+");
//                            Toast.makeText(getView().getContext(), myMoviename, Toast.LENGTH_SHORT).show();
                        }
                    } else myMoviename = myMoviename.replace(" ", "+");
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query="+myMoviename+"&sp=EgIYAg%253D%253D\n")));
//                    intent.putExtra("movie", "https://www.tinyzone.tv/search/" + myMoviename);
//                    startActivity(intent);

                }
            });
       /* {
            Button source2 = getView().findViewById(R.id.sourece2);
            source2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(getView().getContext(), WatchMovieActivity.class);
                    myMoviename = movieName;
                    if ((myMoviename.contains(":")) || (myMoviename.contains("(")) || (myMoviename.contains(".")) || (myMoviename.contains(",")) || (myMoviename.contains("-"))) {
                        int iend = myMoviename.indexOf(":");
                        if ((myMoviename.contains("("))) iend = myMoviename.indexOf("(") - 1;
                        if ((myMoviename.contains("."))) iend = myMoviename.indexOf(".");

                        if (iend != -1) {
                            myMoviename = myMoviename.substring(0, iend);
                            myMoviename = myMoviename.replace(" ", "+");
//                            Toast.makeText(getView().getContext(), myMoviename, Toast.LENGTH_SHORT).show();
                        }
                    } else myMoviename = myMoviename.replace(" ", "-");
                    intent.putExtra("movie", "https://just-watch-it.com/search?q=" + myMoviename);
                    startActivity(intent);
                }
            });

            Button source3 = getView().findViewById(R.id.sourece3);
            source3.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(getView().getContext(), WatchMovieActivity.class);
                    myMoviename = movieName;
                    if ((myMoviename.contains(":")) || (myMoviename.contains("(")) || (myMoviename.contains(".")) || (myMoviename.contains(",")) || (myMoviename.contains("-"))) {
                        int iend = myMoviename.indexOf(":");
                        if ((myMoviename.contains("("))) iend = myMoviename.indexOf("(") - 1;
                        if ((myMoviename.contains("."))) iend = myMoviename.indexOf(".");

                        if (iend != -1) {
                            myMoviename = myMoviename.substring(0, iend);
                            myMoviename = myMoviename.replace(" ", "+");
//                            Toast.makeText(getView().getContext(), myMoviename, Toast.LENGTH_SHORT).show();
                        }
                    } else myMoviename = myMoviename.replace(" ", "-");
                    intent.putExtra("movie", "https://ww1.putlocker.vip/movie/search/" + myMoviename);
                    startActivity(intent);
                }
            });

            Button source4 = getView().findViewById(R.id.sourece4);
            source4.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(getView().getContext(), WatchMovieActivity.class);
                    myMoviename = movieName;
                    if ((myMoviename.contains(":")) || (myMoviename.contains("(")) || (myMoviename.contains(".")) || (myMoviename.contains(",")) || (myMoviename.contains("-"))) {
                        int iend = myMoviename.indexOf(":");
                        if ((myMoviename.contains("("))) iend = myMoviename.indexOf("(") - 1;
                        if ((myMoviename.contains("."))) iend = myMoviename.indexOf(".");

                        if (iend != -1) {
                            myMoviename = myMoviename.substring(0, iend);
                            myMoviename = myMoviename.replace(" ", "+");
//                            Toast.makeText(getView().getContext(), myMoviename, Toast.LENGTH_SHORT).show();
                        }
                    } else myMoviename = myMoviename.replace(" ", "-");
                    intent.putExtra("movie", "https://moviewatcher.is/search?query=" + myMoviename);
                    startActivity(intent);
                }
            });
        }*/

        if (trailers.isEmpty()) {
            label.setVisibility(View.GONE);
            this.trailers.setVisibility(View.GONE);
            horizontalScrollView.setVisibility(View.GONE);

        }
        else {
            label.setVisibility(View.VISIBLE);
            this.trailers.setVisibility(View.VISIBLE);
            horizontalScrollView.setVisibility(View.VISIBLE);

            this.trailers.removeAllViews();
            LayoutInflater inflater = getActivity().getLayoutInflater();
            RequestOptions options = new RequestOptions()
                    .placeholder(R.color.colorPrimary)
                    .centerCrop()
                    .override(150, 150);

            for (Video trailer : trailers) {
                View thumbContainer = inflater.inflate(R.layout.video, this.trailers, false);
                ImageView thumbView = thumbContainer.findViewById(R.id.video_thumb);
                thumbView.setTag(R.id.glide_tag, Video.getUrl(trailer));
                thumbView.requestLayout();
                thumbView.setOnClickListener(this);
                Glide.with(requireContext())
                        .load(Video.getThumbnailUrl(trailer))
                        .apply(options)
                        .into(thumbView);
                this.trailers.addView(thumbContainer);
            }
        }
    }

    @Override
    public void showReviews(List<Review> reviews) {
        if (reviews.isEmpty()) {
            this.reviews.setVisibility(View.GONE);
            reviewsContainer.setVisibility(View.GONE);
        } else {
            this.reviews.setVisibility(View.VISIBLE);
            reviewsContainer.setVisibility(View.VISIBLE);

            reviewsContainer.removeAllViews();
            LayoutInflater inflater = getActivity().getLayoutInflater();
            for (Review review : reviews) {
                ViewGroup reviewContainer = (ViewGroup) inflater.inflate(R.layout.review, reviewsContainer, false);
                TextView reviewAuthor = reviewContainer.findViewById(R.id.review_author);
                TextView reviewContent = reviewContainer.findViewById(R.id.review_content);
                reviewAuthor.setText(review.getAuthor());
                reviewContent.setText(review.getContent());
                reviewContent.setOnClickListener(this);
                reviewsContainer.addView(reviewContainer);
            }
        }
    }

    @Override
    public void showFavorited() {
        favorite.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_favorite_white_24dp));
    }

    @Override
    public void showUnFavorited() {
        favorite.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_favorite_border_white_24dp));
    }

    @OnClick(R.id.favorite)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.video_thumb:
                onThumbnailClick(view);
                break;

            case R.id.review_content:
                onReviewClick((TextView) view);
                break;

            case R.id.favorite:
                onFavoriteClick();
                break;

            default:
                break;
        }
    }

    private void onReviewClick(TextView view) {
        if (view.getMaxLines() == 5) {
            view.setMaxLines(500);
        } else {
            view.setMaxLines(5);
        }
    }

    private void onThumbnailClick(View view) {
        String videoUrl = (String) view.getTag(R.id.glide_tag);
        Intent playVideoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        startActivity(playVideoIntent);
    }

    private void onFavoriteClick() {
        movieDetailsPresenter.onFavoriteClick(movie);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        movieDetailsPresenter.destroy();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((BaseApplication) getActivity().getApplication()).releaseDetailsComponent();
    }
}
