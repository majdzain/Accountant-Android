package com.zezoo.accountant;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.ExpandableBadgeDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    FloatingActionMenu fam;
    FloatingActionButton fab_tem;
    FloatingActionButton fab_sal;
    FloatingActionButton fab_pur;
    Intent i;
    Resources res;
    Menu men;
    //save our header or result
    private AccountHeader headerResult = null;
    Drawer result = null;
    ListView listView;
    List<ListItem> listItems;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBar;
    Context context = this;
    BillSQLDatabaseHandler dbb;
    SubjectSQLDatabaseHandler dbs;
    CustomerSQLDatabaseHandler dbc;

    private static final int ADD_POINT = 100000;
    private static final int EDIT_POINTS = 100001;

    final int control = 1, purchases = 2, sales = 3, temporary = 4, products = 5, customer = 6, suppliers = 7, expenses = 8, bonds = 9, exchange = 10, calculator = 11, backup = 12, settings = 13, help = 14, contact = 15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signupAsFirst();
        setContentView(R.layout.activity_main);
        res = getResources();
        toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        toolbar.setTitle("Accountant");
        toolbar.setTitleTextColor(res.getColor(R.color.white));
        toolbar.setBackgroundColor(res.getColor(R.color.orange));
        setSupportActionBar(toolbar);
        /**Locale myLocale = new Locale("ar");
         DisplayMetrics dm = res.getDisplayMetrics();
         Configuration conf = res.getConfiguration();
         conf.locale = myLocale;
         res.updateConfiguration(conf, dm);**/
        dbb = new BillSQLDatabaseHandler(this);
        dbs = new SubjectSQLDatabaseHandler(this);
        dbc = new CustomerSQLDatabaseHandler(this);
        dbb.allBills();
        dbs.allSubjects();
        dbc.allCustomers();
        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(R.drawable.m).withIdentifier(100).withSelectedTextColorRes(R.color.spec_black);
        // Create the AccountHeader
        // Tie DrawerLayout events to the ActionBarToggle
        headerResult = new AccountHeaderBuilder()
                .withActivity(this).withHeaderBackground(R.drawable.header10)
                .withTranslucentStatusBar(true).withTextColorRes(R.color.white)
                .addProfiles(profile,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        new ProfileSettingDrawerItem().withName(R.string.drawer_add_point).withIcon(GoogleMaterial.Icon.gmd_add).withIdentifier(ADD_POINT),
                        new ProfileSettingDrawerItem().withName(R.string.drawer_edit_point).withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(EDIT_POINTS)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        //sample usage of the onProfileChanged listener
                        //if the clicked item has the identifier 1 add a new profile ;)
                        if (profile instanceof IDrawerItem && profile.getIdentifier() == ADD_POINT) {
                            int count = 100 + headerResult.getProfiles().size() + 1;
                            IProfile newProfile = new ProfileDrawerItem().withNameShown(true).withName("Batman" + count).withEmail("batman" + count + "@gmail.com").withIcon(R.drawable.profile).withIdentifier(count);
                            if (headerResult.getProfiles() != null) {
                                //we know that there are 2 setting elements. set the new profile above them ;)
                                headerResult.addProfile(newProfile, headerResult.getProfiles().size() - 2);
                            } else {
                                headerResult.addProfiles(newProfile);
                            }
                        }

                        //false if you have not consumed the event and it should close the drawer
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

//create the drawer and remember the `Drawer` result object
        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new SectionDrawerItem().withName(R.string.section_basic),
                        new PrimaryDrawerItem().withName(R.string.drawer_control).withIcon(GoogleMaterial.Icon.gmd_dashboard).withIdentifier(control).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.orange).withIconColorRes(R.color.orange),
                        new ExpandableBadgeDrawerItem().withName(R.string.drawer_invoices).withIcon(GoogleMaterial.Icon.gmd_description).withSelectable(false).withIconColorRes(R.color.orange).withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.darker_blue)).withBadge("100").withSubItems(
                                new SecondaryDrawerItem().withName(R.string.drawer_purchases).withLevel(2).withIcon(GoogleMaterial.Icon.gmd_fiber_manual_record).withIdentifier(purchases).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.blue).withIconColorRes(R.color.blue),
                                new SecondaryDrawerItem().withName(R.string.drawer_sales).withLevel(2).withIcon(GoogleMaterial.Icon.gmd_fiber_manual_record).withIdentifier(sales).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.purple).withIconColorRes(R.color.purple),
                                new SecondaryDrawerItem().withName(R.string.drawer_temporary).withLevel(2).withIcon(GoogleMaterial.Icon.gmd_fiber_manual_record).withIdentifier(temporary).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.green).withIconColorRes(R.color.green)),
                        new PrimaryDrawerItem().withName(R.string.drawer_products).withIcon(GoogleMaterial.Icon.gmd_inbox).withIdentifier(products).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.orange).withIconColorRes(R.color.orange),
                        new PrimaryDrawerItem().withName(R.string.drawer_customers).withIcon(GoogleMaterial.Icon.gmd_person_pin).withIdentifier(customer).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.orange).withIconColorRes(R.color.orange),
                        new PrimaryDrawerItem().withName(R.string.drawer_suppliers).withIcon(GoogleMaterial.Icon.gmd_person).withIdentifier(suppliers).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.orange).withIconColorRes(R.color.orange),
                        new PrimaryDrawerItem().withName(R.string.drawer_expenses).withIcon(GoogleMaterial.Icon.gmd_attach_money).withIdentifier(expenses).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.orange).withIconColorRes(R.color.orange),
                        new PrimaryDrawerItem().withName(R.string.drawer_bonds).withIcon(GoogleMaterial.Icon.gmd_assignment).withIdentifier(bonds).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.orange).withIconColorRes(R.color.orange),
                        new SectionDrawerItem().withName(R.string.section_tools),
                        new PrimaryDrawerItem().withName(R.string.drawer_exchange).withIcon(GoogleMaterial.Icon.gmd_swap_vert).withIdentifier(exchange).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.orange).withIconColorRes(R.color.orange),
                        new PrimaryDrawerItem().withName(R.string.drawer_calculator).withIcon(R.drawable.edit_calculator).withIdentifier(calculator).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.orange).withIconColorRes(R.color.orange),
                        new SectionDrawerItem().withName(R.string.section_preferences),
                        new PrimaryDrawerItem().withName(R.string.drawer_backup).withIcon(GoogleMaterial.Icon.gmd_backup).withIdentifier(backup).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.orange).withIconColorRes(R.color.orange),
                        new PrimaryDrawerItem().withName(R.string.drawer_settings).withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(settings).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.orange).withIconColorRes(R.color.orange),
                        new PrimaryDrawerItem().withName(R.string.drawer_help).withIcon(GoogleMaterial.Icon.gmd_help).withIdentifier(help).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.orange).withIconColorRes(R.color.orange),
                        new PrimaryDrawerItem().withName(R.string.drawer_contact_us).withIcon(GoogleMaterial.Icon.gmd_copyright).withIdentifier(contact).withSelectedTextColorRes(R.color.spec_black).withSelectedIconColorRes(R.color.orange).withIconColorRes(R.color.orange)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //creating fragment object
                        Fragment fragment = null;
                        //initializing the fragment object which is selected
                        switch ((int) drawerItem.getIdentifier()) {
                            case control:
                                fragment = new ControlActivity();
                                toolbar.setBackgroundColor(res.getColor(R.color.orange));
                                toolbar.setTitle("Accountant");
                                break;
                            case purchases:
                                fragment = new PurchasesActivity();
                                toolbar.setBackgroundColor(res.getColor(R.color.blue));
                                toolbar.setTitle(R.string.drawer_purchases);
                                toolbar.inflateMenu(R.menu.menu_control);
                                break;
                            case sales:
                                fragment = new SalesActivity();
                                toolbar.setBackgroundColor(res.getColor(R.color.purple));
                                toolbar.setTitle(R.string.drawer_sales);
                                break;
                            case temporary:
                                fragment = new TemporaryActivity();
                                toolbar.setBackgroundColor(res.getColor(R.color.green));
                                toolbar.setTitle(R.string.drawer_temporary);
                                break;
                            case products:
                                fragment = new ListSubjectsActivity();
                                toolbar.setBackgroundColor(res.getColor(R.color.light_green));
                                toolbar.setTitle(R.string.drawer_products);
                                break;
                            case customer:
                                fragment = new ListCustomersActivity();
                                toolbar.setBackgroundColor(res.getColor(R.color.yellow));
                                toolbar.setTitle(R.string.drawer_customers);
                                break;
                            case suppliers:
                                fragment = new ListSuppliersActivity();
                                toolbar.setBackgroundColor(res.getColor(R.color.colorAccent));
                                toolbar.setTitle(R.string.drawer_suppliers);
                                break;
                            case expenses:
                                fragment = new ListExpensesActivity();
                                toolbar.setBackgroundColor(res.getColor(R.color.light_red));
                                toolbar.setTitle(R.string.drawer_expenses);
                                break;
                            case bonds:
                                fragment = new ListBondsActivity();
                                toolbar.setBackgroundColor(res.getColor(R.color.darker_red));
                                toolbar.setTitle(R.string.drawer_bonds);
                                break;
                            case exchange:
                                fragment = new ListCustomersActivity();
                                break;
                            case calculator:
                                fragment = new ListCustomersActivity();
                                break;
                            case backup:
                                fragment = new ListCustomersActivity();
                                break;
                            case settings:
                                fragment = new ListCustomersActivity();
                                break;
                            case help:
                                fragment = new ListCustomersActivity();
                                break;
                            case contact:
                                fragment = new ListCustomersActivity();
                                break;

                        }
                        //replacing the fragment
                        if (fragment != null) {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.fragmentMain, fragment);
                            ft.commit();
                            result.closeDrawer();
                        }
                        return true;
                    }
                }).build();
        result.setSelection(1);
        actionBar = result.getActionBarDrawerToggle();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        actionBar.setDrawerIndicatorEnabled(true);
        actionBar.setDrawerSlideAnimationEnabled(true);
    }

    private void signupAsFirst() {
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        if (isFirstRun) {
            //show sign up activity
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentMain);
        if ((int)result.getCurrentSelection() == 5 && ((ListSubjectsActivity) fragment).isCheckEnabled) {
            List<SubjectListChildItem> subjects = ((ListSubjectsActivity) fragment).db.allSubjects();
            List<String> folders = ((ListSubjectsActivity) fragment).db.allFolders();
            int index = ((ListSubjectsActivity) fragment).expListView.getFirstVisiblePosition();
            View v = ((ListSubjectsActivity) fragment).expListView.getChildAt(0);
            int top = (v == null) ? 0 : (v.getTop() - ((ListSubjectsActivity) fragment).expListView.getPaddingTop());
            boolean x[] = new boolean[folders.size()];
            for (int i = 0; i < folders.size(); i++) {
                x[i] = ((ListSubjectsActivity) fragment).expListView.isGroupExpanded(i);
            }
            ((ListSubjectsActivity) fragment).createListFromSQL();
            ((ListSubjectsActivity) fragment).isCheckEnabled = false;
            ((ListSubjectsActivity) fragment).menuRecordItem.setVisible(true);
            ((ListSubjectsActivity) fragment).menuExtractItem.setVisible(false);
            ((ListSubjectsActivity) fragment).menuDeleteItem.setVisible(false);
            ((ListSubjectsActivity) fragment).menuSelectaItem.setVisible(false);
            ((ListSubjectsActivity) fragment).menuDeselectaItem.setVisible(false);
            ((ListSubjectsActivity) fragment).menuDeleteaItem.setVisible(true);
            ((ListSubjectsActivity) fragment).menuSearchItem.setVisible(true);
            ((ListSubjectsActivity) fragment).menuNewItem.setVisible(true);
            for (int i = 0; i < folders.size(); i++) {
                if (x[i])
                    ((ListSubjectsActivity) fragment).expListView.expandGroup(i);
            }
            ((ListSubjectsActivity) fragment).expListView.setSelectionFromTop(index, top);
        }else if((int)result.getCurrentSelection() == 2 && ((PurchasesActivity) fragment).isCheckEnabled){
            List<BillListChildItem> billss = ((PurchasesActivity) fragment).db.allBills();
            List<String> folders = ((PurchasesActivity) fragment).db.allSupplierFolders();
            int index = ((PurchasesActivity) fragment).expListView.getFirstVisiblePosition();
            View v = ((PurchasesActivity) fragment).expListView.getChildAt(0);
            int top = (v == null) ? 0 : (v.getTop() - ((PurchasesActivity) fragment).expListView.getPaddingTop());
            boolean x[] = new boolean[folders.size()];
            for (int i = 0; i < folders.size(); i++) {
                x[i] = ((PurchasesActivity) fragment).expListView.isGroupExpanded(i);
            }
            ((PurchasesActivity) fragment).createListFromSQL();
            ((PurchasesActivity) fragment).isCheckEnabled = false;
            ((PurchasesActivity) fragment).menuInfoItem.setVisible(false);
            ((PurchasesActivity) fragment).menuRecordItem.setVisible(true);
            ((PurchasesActivity) fragment).menuDeleteItem.setVisible(false);
            ((PurchasesActivity) fragment).menuSelectaItem.setVisible(false);
            ((PurchasesActivity) fragment).menuDeselectaItem.setVisible(false);
            ((PurchasesActivity) fragment).menuDeleteaItem.setVisible(true);
            ((PurchasesActivity) fragment).menuSearchItem.setVisible(true);
            ((PurchasesActivity) fragment).menuNewItem.setVisible(true);
            for (int i = 0; i < folders.size(); i++) {
                if (x[i])
                    ((PurchasesActivity) fragment).expListView.expandGroup(i);
            }
            ((PurchasesActivity) fragment).expListView.setSelectionFromTop(index, top);
        }else if((int)result.getCurrentSelection() == 3 && ((SalesActivity) fragment).isCheckEnabled){
            List<BillListChildItem> billss = ((SalesActivity) fragment).db.allBills();
            List<String> folders = ((SalesActivity) fragment).db.allSupplierFolders();
            int index = ((SalesActivity) fragment).expListView.getFirstVisiblePosition();
            View v = ((SalesActivity) fragment).expListView.getChildAt(0);
            int top = (v == null) ? 0 : (v.getTop() - ((SalesActivity) fragment).expListView.getPaddingTop());
            boolean x[] = new boolean[folders.size()];
            for (int i = 0; i < folders.size(); i++) {
                x[i] = ((SalesActivity) fragment).expListView.isGroupExpanded(i);
            }
            ((SalesActivity) fragment).createListFromSQL();
            ((SalesActivity) fragment).isCheckEnabled = false;
            ((SalesActivity) fragment).menuInfoItem.setVisible(false);
            ((SalesActivity) fragment).menuRecordItem.setVisible(true);
            ((SalesActivity) fragment).menuDeleteItem.setVisible(false);
            ((SalesActivity) fragment).menuSelectaItem.setVisible(false);
            ((SalesActivity) fragment).menuDeselectaItem.setVisible(false);
            ((SalesActivity) fragment).menuDeleteaItem.setVisible(true);
            ((SalesActivity) fragment).menuSearchItem.setVisible(true);
            ((SalesActivity) fragment).menuNewItem.setVisible(true);
            for (int i = 0; i < folders.size(); i++) {
                if (x[i])
                    ((SalesActivity) fragment).expListView.expandGroup(i);
            }
            ((SalesActivity) fragment).expListView.setSelectionFromTop(index, top);
        }else{
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                closeApplication();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, res.getString(R.string.alertPressBack), Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + listItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    public void closeApplication() {
        finish();
        finishAffinity();
        moveTaskToBack(true);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}