package com.example.snailpasswordmanager.presentation.mainscreen;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB\u0005\u00a2\u0006\u0002\u0010\u0003J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u0018\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u0014\u0010\u0018\u001a\u00020\r2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001aR0\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u00a8\u0006\u001c"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordListAdapter$PasswordItemViewHolder;", "()V", "value", "Ljava/util/ArrayList;", "Lcom/example/snailpasswordmanager/domain/model/PasswordEntity;", "list", "getList", "()Ljava/util/ArrayList;", "setList", "(Ljava/util/ArrayList;)V", "addPassword", "", "passwordEntity", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setPasswords", "li", "", "PasswordItemViewHolder", "app_debug"})
public final class PasswordListAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.snailpasswordmanager.presentation.mainscreen.PasswordListAdapter.PasswordItemViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.example.snailpasswordmanager.domain.model.PasswordEntity> list;
    
    public PasswordListAdapter() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.example.snailpasswordmanager.domain.model.PasswordEntity> getList() {
        return null;
    }
    
    public final void setList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.example.snailpasswordmanager.domain.model.PasswordEntity> value) {
    }
    
    public final void setPasswords(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.snailpasswordmanager.domain.model.PasswordEntity> li) {
    }
    
    public final void addPassword(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.model.PasswordEntity passwordEntity) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.snailpasswordmanager.presentation.mainscreen.PasswordListAdapter.PasswordItemViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.presentation.mainscreen.PasswordListAdapter.PasswordItemViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\r"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordListAdapter$PasswordItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "binding", "Lcom/example/snailpasswordmanager/databinding/PasswordItemBinding;", "getBinding", "()Lcom/example/snailpasswordmanager/databinding/PasswordItemBinding;", "bind", "", "passwordEntity", "Lcom/example/snailpasswordmanager/domain/model/PasswordEntity;", "app_debug"})
    public static final class PasswordItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.example.snailpasswordmanager.databinding.PasswordItemBinding binding = null;
        
        public PasswordItemViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.snailpasswordmanager.databinding.PasswordItemBinding getBinding() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.snailpasswordmanager.domain.model.PasswordEntity passwordEntity) {
        }
    }
}