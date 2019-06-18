package com.example.swipeimageviewersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.swipe_fragment.*
import kotlinx.android.synthetic.main.swipe_fragment.view.*

val NUM_ITEMS = 6
var imageFragmentPagerAdapter: ImageFragmentPagerAdapter? = null
var viewPager: ViewPager? = null
val IMAGE_NAME = arrayOf("img_1537", "img_1538", "img_1540", "img_1541", "img_1543", "img_1549")


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = supportFragmentManager
        val viewPager = pager as ViewPager
        val adapter = ImageFragmentPagerAdapter(manager)
        viewPager.adapter = adapter
    }

}

class ImageFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): Fragment {
        return SwipeFragment.newInstance(position)
    }
}

class SwipeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val swipeView = inflater.inflate(R.layout.swipe_fragment, container, false)
        val imageView = swipeView.imageView as ImageView
        val bundle = arguments
        val position = bundle!!.getInt("position")
        val imageFileName = IMAGE_NAME[position]
        val imgResId = resources.getIdentifier(imageFileName, "drawable", "com.example.swipeimageviewersample")
        imageView.setImageResource(imgResId)
        return swipeView
    }

    companion object {
        internal fun newInstance(position: Int): SwipeFragment {
            val swipeFragment = SwipeFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            swipeFragment.arguments = bundle
            return swipeFragment
        }
    }
}
