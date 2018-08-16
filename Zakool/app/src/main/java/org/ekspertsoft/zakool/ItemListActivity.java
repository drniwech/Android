package org.ekspertsoft.zakool;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.ekspertsoft.zakool.dto.School;
import org.ekspertsoft.zakool.utils.StringConversion;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private EditText minEditText, maxEditText;
    private final String BASED_URL = "http://zakool-ekspertsoft.rhcloud.com/";
    private List<School> SCHOOLS = new ArrayList<School>();
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        //Spinner
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        // receive the arguments from the previous Activity
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        final String minValue = extras.getString("minValue");
        final String maxValue = extras.getString("maxValue");
        final String nameValue = extras.getString("name");
        final StringBuilder builder = new StringBuilder(BASED_URL);
        if(null!=minValue && null!=maxValue) {
            //builder.append(BASED_URL);
            builder.append("search/");
            builder.append(minValue);
            builder.append("/");
            builder.append(maxValue);
            //AsyncTask call
            new HttpRequestTask().execute(builder.toString());
        }else if(null==minValue && null==maxValue && null!=nameValue && !nameValue.isEmpty()){
            builder.append("name/");
            builder.append(nameValue);
            //AsyncTask call
            new HttpRequestTask().execute(builder.toString());
        }

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        //add line separator
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //set adapter
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(SCHOOLS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<School> mValues;

        public SimpleItemRecyclerViewAdapter(List<School> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            final School school = mValues.get(position);
            final String name = String.format("%1$s . %2$s", position+1, school.getName());
            holder.mNameView.setText(name);
            final String total = StringConversion.toUSCurrencyFormat(school.getTotalPriceInStateOnCampus());
            final String category = StringConversion.toCategory(school.getCategory());
            final String state = StringConversion.toState(school.getState());
            final String detail = String.format("%1$s/year. %2$s. State: %3$s", total, category, state);
            holder.mDetailView.setText(detail);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        //arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.getName());
                        arguments.putSerializable(ItemDetailFragment.ARG_ITEM_ID, holder.mItem);
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        //intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.getName());
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.mItem);
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mNameView;
            public final TextView mDetailView;
            public School mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mNameView = (TextView) view.findViewById(R.id.name);
                mDetailView = (TextView) view.findViewById(R.id.detail);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mNameView.getText() + "'";
            }
        }
    }

    private class HttpRequestTask extends AsyncTask<String, Void, List<School>> {
        @Override
        protected List<School> doInBackground(String... params) {
            List<School> results = new ArrayList<School>();
            try {
                //final String url = "http://zakool-ekspertsoft.rhcloud.com/search/"+minEditText +"/"+maxTextFieldValue;
                final String url = params.length>0? params[0]: BASED_URL;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<School[]> responseEntity = restTemplate.getForEntity(url, School[].class);
                results = Arrays.asList(responseEntity.getBody());
                return results;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            //return empty list if there is exception.
            return results;
        }

        @Override
        protected void onPostExecute(List<School> schools) {
            if(!schools.isEmpty()) {
                SCHOOLS.addAll(schools);
                View recyclerView = findViewById(R.id.item_list);
                assert recyclerView != null;
                setupRecyclerView((RecyclerView) recyclerView);
            }
            //stop spinner
            spinner.setVisibility(View.GONE);
        }

    }
}
