package fr.nikho.epitech.intra.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.data.Dashboard;

public class HomeProjectItemView extends LinearLayout {

    private Dashboard.Board.Project project;
    private LayoutInflater inflater;
    private LinearLayout linearLayout;

    private TextView projectTitle;

    public HomeProjectItemView(Context context, Dashboard.Board.Project project) {
        super(context);
        this.project = project;

        inflater = LayoutInflater.from(context);
        linearLayout = (LinearLayout)inflater.inflate(R.layout.home_project_item, null);
        projectTitle = linearLayout.findViewById(R.id.project_title);
        projectTitle.setText("Hello World ;)");
    }

    public LayoutInflater getInflater() {
        return this.inflater;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
