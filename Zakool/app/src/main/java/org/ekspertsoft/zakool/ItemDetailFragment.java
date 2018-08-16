package org.ekspertsoft.zakool;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ekspertsoft.zakool.dto.School;
import org.ekspertsoft.zakool.utils.StringConversion;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "school_item";

    /**
     * The dummy content this fragment is presenting.
     */
    private School schoolItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the content specified by the fragment
            // arguments.
            schoolItem = (School) getArguments().getSerializable(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(schoolItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the content as text in a TextView.
        if (schoolItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail_name)).setText(schoolItem.getName());
            final String state = StringConversion.toState(schoolItem.getState());
            final String address = String.format("Address: %1$s, %2$s, %3$s %4$s",
                                                schoolItem.getStreet(), schoolItem.getCity(),
                                                state, schoolItem.getZipcode() );
            ((TextView) rootView.findViewById(R.id.item_detail_address)).setText(address);

            final String degree = String.format("Degree: %1$s", StringConversion.toCategory(schoolItem.getCategory()));
            ((TextView) rootView.findViewById(R.id.item_detail_degree)).setText(degree);

            final String phone = String.format("Phone: %1$s", schoolItem.getPhone());
            ((TextView) rootView.findViewById(R.id.item_detail_phone)).setText(phone);

            final String type = String.format("Institution Type: %1$s", StringConversion.toType(schoolItem.getType()));
            ((TextView) rootView.findViewById(R.id.item_detail_type)).setText(type);

            final String size = String.format("Institution Size: %1$s", StringConversion.toSize(schoolItem.getSize()));
            ((TextView) rootView.findViewById(R.id.item_detail_size)).setText(size);

            TextView urlView = ((TextView) rootView.findViewById(R.id.item_detail_url));
            urlView.setText(schoolItem.getUrl());
            //set up URL and onClickListener
            /*urlView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Log.d("url", schoolItem.getUrl());
                    final String url = schoolItem.getUrl().contains("http://")? schoolItem.getUrl()
                                                : "http://"+schoolItem.getUrl();
                    Uri uriUrl = Uri.parse(url);
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }
            });*/

            final String level = String.format("Level of institution: %1$s", StringConversion.toLevel(schoolItem.getLevel()));
            ((TextView) rootView.findViewById(R.id.item_detail_level)).setText(level);

            final String totalInStateOnCampus = String.format("Total In-State, On Campus: %1$s",
                    StringConversion.toUSCurrencyFormat(schoolItem.getTotalPriceInStateOnCampus()));
            ((TextView) rootView.findViewById(R.id.item_detail_total_instate_oncampus)).setText(totalInStateOnCampus);

            final String totalInStateOffCampus = String.format("Total In-State, Off Campus: %1$s",
                    StringConversion.toUSCurrencyFormat(schoolItem.getTotalPriceInStateOffCampus()));
            ((TextView) rootView.findViewById(R.id.item_detail_total_instate_offcampus)).setText(totalInStateOffCampus);

            final String totalOutStateOnCampus = String.format("Total Out-State, On Campus: %1$s",
                    StringConversion.toUSCurrencyFormat(schoolItem.getTotalPriceOffStateOnCampus()));
            ((TextView) rootView.findViewById(R.id.item_detail_total_outstate_oncampus)).setText(totalOutStateOnCampus);

            final String totalOutStateOffCampus = String.format("Total Out-State, Off Campus: %1$s",
                    StringConversion.toUSCurrencyFormat(schoolItem.getTotalPriceOffStateOffCampus()));
            ((TextView) rootView.findViewById(R.id.item_detail_total_outstate_offcampus)).setText(totalOutStateOffCampus);
        }

        return rootView;
    }

}
