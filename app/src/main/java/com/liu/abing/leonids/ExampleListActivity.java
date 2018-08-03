package com.liu.abing.leonids;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liu.abing.leonids.leonids.AnimatedParticlesExampleActivity;
import com.liu.abing.leonids.leonids.ConfettiExampleActivity;
import com.liu.abing.leonids.leonids.DustExampleActivity;
import com.liu.abing.leonids.leonids.EmiterBackgroundSimpleExampleActivity;
import com.liu.abing.leonids.leonids.EmiterIntermediateExampleActivity;
import com.liu.abing.leonids.leonids.EmiterSimpleExampleActivity;
import com.liu.abing.leonids.leonids.EmiterTimeLimitedExampleActivity;
import com.liu.abing.leonids.leonids.EmiterWithGravityExampleActivity;
import com.liu.abing.leonids.leonids.FireworksExampleActivity;
import com.liu.abing.leonids.leonids.FollowCursorExampleActivity;
import com.liu.abing.leonids.leonids.OneShotAdvancedExampleActivity;
import com.liu.abing.leonids.leonids.OneShotSimpleExampleActivity;
import com.liu.abing.leonids.leonids.StarsExampleActivity;

public class ExampleListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String sampleList[] = new String [] {
				"One Shot Simple",
				"One Shot Advanced"
				, "Emiter Simple"
                , "Emiting on background [NEW]"
                , "Emiter Intermediate"
                , "彩带"
				, "雪花"
				, "气泡"
				, "Animated particles"
				, "Fireworks"
				, "左下角出花瓣"
				, "Dust [Rabbit and Eggs]"
				, "Stars [Rabbit and Eggs]"
				};
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sampleList));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0:
			startActivity(new Intent(this, OneShotSimpleExampleActivity.class));
			break;
		case 1:
			startActivity(new Intent(this, OneShotAdvancedExampleActivity.class));
			break;
		case 2:
			startActivity(new Intent(this, EmiterSimpleExampleActivity.class));
			break;
        case 3:
            startActivity(new Intent(this, EmiterBackgroundSimpleExampleActivity.class));
            break;
		case 4:
			startActivity(new Intent(this, EmiterIntermediateExampleActivity.class));
			break;
		case 5://彩带
			startActivity(new Intent(this, EmiterTimeLimitedExampleActivity.class));
			break;
		case 6://雪花
			startActivity(new Intent(this, EmiterWithGravityExampleActivity.class));
			break;
		case 7:
			startActivity(new Intent(this, FollowCursorExampleActivity.class));
			break;
		case 8:
			startActivity(new Intent(this, AnimatedParticlesExampleActivity.class));
			break;
		case 9:
			startActivity(new Intent(this, FireworksExampleActivity.class));
			break;
		case 10:
			startActivity(new Intent(this, ConfettiExampleActivity.class));
			break;
		case 11:
			startActivity(new Intent(this, DustExampleActivity.class));
			break;
		case 12:
			startActivity(new Intent(this, StarsExampleActivity.class));
			break;
			default:
				break;
		}
	}

}
