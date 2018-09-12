package com.gulden.unity_wallet

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.gulden.jniunifiedbackend.GuldenUnifiedBackend
import com.gulden.jniunifiedbackend.GuldenUnifiedFrontendImpl
import com.gulden.unity_wallet.SendFragment.OnFragmentInteractionListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction{replace(frameId, fragment)}
}

class MainActivity : AppCompatActivity(), OnFragmentInteractionListener, ReceiveFragment.OnFragmentInteractionListener, TransactionFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener  {

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented")
    }

    private val sendFragment : Fragment = SendFragment();
    private val receiveFragment : Fragment = ReceiveFragment();
    private val transactionFragment : Fragment = TransactionFragment();
    private val settingsFragment : Fragment = SettingsFragment();

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_send -> {
                replaceFragment(sendFragment, R.id.mainLayout)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_receive -> {
                replaceFragment(receiveFragment, R.id.mainLayout)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_transactions -> {
                replaceFragment(transactionFragment, R.id.mainLayout)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                replaceFragment(settingsFragment, R.id.mainLayout)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val coreSignals: GuldenUnifiedFrontendImpl?=GuldenUnifiedFrontendImpl()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coreSignals?.activity = this

        thread(start = true) {
            System.loadLibrary("gulden_unity_jni")
            GuldenUnifiedBackend.InitUnityLib(applicationContext.getApplicationInfo().dataDir, coreSignals)
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        syncProgress.setMax(1000000);
        syncProgress.setProgress(0);

        addFragment(sendFragment, R.id.mainLayout)
    }

    public fun updateSyncProgressPercent(percent : Float)
    {
        syncProgress.setProgress((1000000 * (percent/100)).toInt());
    }
}
