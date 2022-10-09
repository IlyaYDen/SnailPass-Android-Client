package com.example.snailpasswordmanager.presentation.mainscreen;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0017H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00a8\u0006\u001c"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/MainListActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordListAdapter;", "bindingClass", "Lcom/example/snailpasswordmanager/databinding/ActivityMainListBinding;", "getBindingClass", "()Lcom/example/snailpasswordmanager/databinding/ActivityMainListBinding;", "setBindingClass", "(Lcom/example/snailpasswordmanager/databinding/ActivityMainListBinding;)V", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "viewModel", "Lcom/example/snailpasswordmanager/presentation/mainscreen/MainListViewModel;", "vmFactory", "Lcom/example/snailpasswordmanager/presentation/mainscreen/MainListViewModelFactory;", "getVmFactory", "()Lcom/example/snailpasswordmanager/presentation/mainscreen/MainListViewModelFactory;", "setVmFactory", "(Lcom/example/snailpasswordmanager/presentation/mainscreen/MainListViewModelFactory;)V", "init", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "app_debug"})
public final class MainListActivity extends androidx.appcompat.app.AppCompatActivity {
    public com.example.snailpasswordmanager.databinding.ActivityMainListBinding bindingClass;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> launcher;
    @javax.inject.Inject()
    public com.example.snailpasswordmanager.presentation.mainscreen.MainListViewModelFactory vmFactory;
    private com.example.snailpasswordmanager.presentation.mainscreen.MainListViewModel viewModel;
    private final com.example.snailpasswordmanager.presentation.mainscreen.PasswordListAdapter adapter = null;
    
    @javax.inject.Inject()
    public MainListActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.snailpasswordmanager.databinding.ActivityMainListBinding getBindingClass() {
        return null;
    }
    
    public final void setBindingClass(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.databinding.ActivityMainListBinding p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.snailpasswordmanager.presentation.mainscreen.MainListViewModelFactory getVmFactory() {
        return null;
    }
    
    public final void setVmFactory(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.presentation.mainscreen.MainListViewModelFactory p0) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
}