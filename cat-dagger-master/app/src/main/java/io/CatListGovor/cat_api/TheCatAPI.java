package io.CatListGovor.cat_api;

public interface TheCatAPI {
    interface Callback {
        void response(CatImagesModel response);
    }

    void GetCats(Callback callback);
}