package com.hackahealth.kodakesalem.mvp.presenter;

import com.hackahealth.kodakesalem.mvp.objects.FormItemObject;
import com.hackahealth.kodakesalem.mvp.objects.ResponseResultFormObject;
import com.hackahealth.kodakesalem.mvp.objects.ResultFormItemObject;
import com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface.FormPresenterInterface;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.FormViewInterface;
import com.hackahealth.kodakesalem.network.APIService;
import com.hackahealth.kodakesalem.network.ApiProvider;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by siavash on 5/18/16.
 */
public class FormPresenter extends MvpBasePresenter<FormViewInterface> implements FormPresenterInterface {
    List<FormItemObject> formItemObjects;
    List<ResultFormItemObject> resultFormItemObjects;
    int firstVisibleFormItem=0;
    private final static int formPerPage =10;
    private APIService mService;
    private int formId;
    private int childId;

    @Override
    public void attachView(FormViewInterface view) {
        super.attachView(view);
        ApiProvider provider= new ApiProvider();
        mService=provider.getTService();

    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }

    @Override
    public void FormSubmit() {
        resultFormItemObjects.addAll(getView().getData());
        getView().clearLastData();

        if(isValid(resultFormItemObjects)){
            if(firstVisibleFormItem+ formPerPage <formItemObjects.size()){
                List<FormItemObject> tempList=new ArrayList<>();
                for(int i=firstVisibleFormItem+formPerPage;i<formItemObjects.size();i++){
                    tempList.add(formItemObjects.get(i));
                }
                firstVisibleFormItem+=formPerPage;
                getView().addViewForm(tempList);
            }else {
            sendFormToApi();
            }
        }else {
            getView().sendError();
        }
    }

    private void sendFormToApi() {
    Call<ResponseResultFormObject> call=mService.sendProject(childId+"",formId+"",resultFormItemObjects);
    }

    private boolean isValid(List<ResultFormItemObject> resultFormItemObjects) {
        return true;
    }

    @Override
    public void backPressed() {

    }

    @Override
    public void initData(int formId,int childId) {
        this.formId =formId;
        this.childId =childId;
        Call <List<FormItemObject> > call=mService.getFormById(formId+"");
        call.enqueue(new Callback<List<FormItemObject>>() {
            @Override
            public void onResponse(Call<List<FormItemObject>> call, Response<List<FormItemObject>> response) {
                if(response.isSuccess()){
                    int size= formPerPage>response.body().size()?formPerPage:response.body().size();
                    getView().addViewForm(response.body().subList(0, size));
                }
            }

            @Override
            public void onFailure(Call<List<FormItemObject> > call, Throwable t) {

            }
        });
    }
}
