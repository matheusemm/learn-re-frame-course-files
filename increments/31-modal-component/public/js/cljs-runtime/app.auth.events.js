goog.provide('app.auth.events');
goog.require('cljs.core');
goog.require('re_frame.core');
goog.require('cljs.reader');
app.auth.events.cheffy_user_key = "cheffy-user";
app.auth.events.set_user_ls_BANG_ = (function app$auth$events$set_user_ls_BANG_(p__25915){
var map__25916 = p__25915;
var map__25916__$1 = (((((!((map__25916 == null))))?(((((map__25916.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__25916.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,map__25916):map__25916);
var auth = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__25916__$1,new cljs.core.Keyword(null,"auth","auth",1389754926));
if(cljs.core.truth_(new cljs.core.Keyword(null,"uid","uid",-1447769400).cljs$core$IFn$_invoke$arity$1(auth))){
return localStorage.setItem(app.auth.events.cheffy_user_key,cljs.core.str.cljs$core$IFn$_invoke$arity$1(auth));
} else {
return null;
}
});
app.auth.events.remove_user_ls_BANG_ = (function app$auth$events$remove_user_ls_BANG_(){
return localStorage.removeItem(app.auth.events.cheffy_user_key);
});
app.auth.events.set_user_interceptors = new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [(re_frame.core.after.cljs$core$IFn$_invoke$arity$1 ? re_frame.core.after.cljs$core$IFn$_invoke$arity$1(app.auth.events.set_user_ls_BANG_) : re_frame.core.after.call(null,app.auth.events.set_user_ls_BANG_))], null);
app.auth.events.remove_user_interceptors = new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [(re_frame.core.after.cljs$core$IFn$_invoke$arity$1 ? re_frame.core.after.cljs$core$IFn$_invoke$arity$1(app.auth.events.remove_user_ls_BANG_) : re_frame.core.after.call(null,app.auth.events.remove_user_ls_BANG_))], null);
var G__25922_25963 = new cljs.core.Keyword(null,"local-store-user","local-store-user",1737970699);
var G__25923_25964 = ((function (G__25922_25963){
return (function (cofx,_){
return cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(cofx,new cljs.core.Keyword(null,"local-store-user","local-store-user",1737970699),cljs.reader.read_string.cljs$core$IFn$_invoke$arity$1(localStorage.getItem(app.auth.events.cheffy_user_key)));
});})(G__25922_25963))
;
(re_frame.core.reg_cofx.cljs$core$IFn$_invoke$arity$2 ? re_frame.core.reg_cofx.cljs$core$IFn$_invoke$arity$2(G__25922_25963,G__25923_25964) : re_frame.core.reg_cofx.call(null,G__25922_25963,G__25923_25964));
re_frame.core.reg_event_fx.cljs$core$IFn$_invoke$arity$3(new cljs.core.Keyword(null,"sign-up","sign-up",-1190725688),app.auth.events.set_user_interceptors,(function (p__25926,p__25927){
var map__25934 = p__25926;
var map__25934__$1 = (((((!((map__25934 == null))))?(((((map__25934.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__25934.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,map__25934):map__25934);
var db = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__25934__$1,new cljs.core.Keyword(null,"db","db",993250759));
var vec__25935 = p__25927;
var _ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__25935,(0),null);
var map__25938 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__25935,(1),null);
var map__25938__$1 = (((((!((map__25938 == null))))?(((((map__25938.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__25938.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,map__25938):map__25938);
var first_name = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__25938__$1,new cljs.core.Keyword(null,"first-name","first-name",-1559982131));
var last_name = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__25938__$1,new cljs.core.Keyword(null,"last-name","last-name",-1695738974));
var email = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__25938__$1,new cljs.core.Keyword(null,"email","email",1415816706));
var password = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__25938__$1,new cljs.core.Keyword(null,"password","password",417022471));
return new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"db","db",993250759),cljs.core.assoc_in(cljs.core.assoc_in(db,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"auth","auth",1389754926),new cljs.core.Keyword(null,"uid","uid",-1447769400)], null),email),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"users","users",-713552705),email], null),new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"id","id",-1388402092),email,new cljs.core.Keyword(null,"profile","profile",-545963874),new cljs.core.PersistentArrayMap(null, 5, [new cljs.core.Keyword(null,"first-name","first-name",-1559982131),first_name,new cljs.core.Keyword(null,"last-name","last-name",-1695738974),last_name,new cljs.core.Keyword(null,"email","email",1415816706),email,new cljs.core.Keyword(null,"password","password",417022471),password,new cljs.core.Keyword(null,"img","img",1442687358),"img/avatar.jpg"], null),new cljs.core.Keyword(null,"saved","saved",288760660),cljs.core.PersistentHashSet.EMPTY,new cljs.core.Keyword(null,"inboxes","inboxes",-619615989),cljs.core.PersistentArrayMap.EMPTY], null)),new cljs.core.Keyword(null,"dispatch","dispatch",1319337009),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"set-active-page","set-active-page",440775698),new cljs.core.Keyword(null,"saved","saved",288760660)], null),new cljs.core.Keyword(null,"navigate-to","navigate-to",-1161651312),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"path","path",-188191168),"/saved"], null)], null);
}));
re_frame.core.reg_event_fx.cljs$core$IFn$_invoke$arity$3(new cljs.core.Keyword(null,"log-in","log-in",526627385),app.auth.events.set_user_interceptors,(function (p__25941,p__25942){
var map__25943 = p__25941;
var map__25943__$1 = (((((!((map__25943 == null))))?(((((map__25943.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__25943.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,map__25943):map__25943);
var db = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__25943__$1,new cljs.core.Keyword(null,"db","db",993250759));
var vec__25944 = p__25942;
var _ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__25944,(0),null);
var map__25947 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__25944,(1),null);
var map__25947__$1 = (((((!((map__25947 == null))))?(((((map__25947.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__25947.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,map__25947):map__25947);
var email = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__25947__$1,new cljs.core.Keyword(null,"email","email",1415816706));
var password = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__25947__$1,new cljs.core.Keyword(null,"password","password",417022471));
var user = cljs.core.get_in.cljs$core$IFn$_invoke$arity$2(db,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"users","users",-713552705),email], null));
var correct_password_QMARK_ = cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(cljs.core.get_in.cljs$core$IFn$_invoke$arity$2(user,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"profile","profile",-545963874),new cljs.core.Keyword(null,"password","password",417022471)], null)),password);
if(cljs.core.not(user)){
return new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"db","db",993250759),cljs.core.assoc_in(db,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"errors","errors",-908790718),new cljs.core.Keyword(null,"email","email",1415816706)], null),"User not found")], null);
} else {
if((!(correct_password_QMARK_))){
return new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"db","db",993250759),cljs.core.assoc_in(db,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"errors","errors",-908790718),new cljs.core.Keyword(null,"email","email",1415816706)], null),"Wrong password")], null);
} else {
if(correct_password_QMARK_){
return new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"db","db",993250759),cljs.core.update_in.cljs$core$IFn$_invoke$arity$4(cljs.core.assoc_in(db,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"auth","auth",1389754926),new cljs.core.Keyword(null,"uid","uid",-1447769400)], null),email),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"errors","errors",-908790718)], null),cljs.core.dissoc,new cljs.core.Keyword(null,"email","email",1415816706)),new cljs.core.Keyword(null,"dispatch","dispatch",1319337009),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"set-active-page","set-active-page",440775698),new cljs.core.Keyword(null,"saved","saved",288760660)], null),new cljs.core.Keyword(null,"navigate-to","navigate-to",-1161651312),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"path","path",-188191168),"/saved"], null)], null);
} else {
return null;
}
}
}
}));
re_frame.core.reg_event_fx.cljs$core$IFn$_invoke$arity$3(new cljs.core.Keyword(null,"log-out","log-out",-2094664701),app.auth.events.remove_user_interceptors,(function (p__25952,_){
var map__25953 = p__25952;
var map__25953__$1 = (((((!((map__25953 == null))))?(((((map__25953.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__25953.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,map__25953):map__25953);
var db = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__25953__$1,new cljs.core.Keyword(null,"db","db",993250759));
return new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"db","db",993250759),cljs.core.assoc_in(db,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"auth","auth",1389754926),new cljs.core.Keyword(null,"uid","uid",-1447769400)], null),null),new cljs.core.Keyword(null,"dispatch","dispatch",1319337009),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"set-active-page","set-active-page",440775698),new cljs.core.Keyword(null,"recipes","recipes",-325236209)], null),new cljs.core.Keyword(null,"navigate-to","navigate-to",-1161651312),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"path","path",-188191168),"/recipes"], null)], null);
}));
re_frame.core.reg_event_db.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"update-profile","update-profile",1458083625),(function (db,p__25956){
var vec__25957 = p__25956;
var _ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__25957,(0),null);
var profile = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__25957,(1),null);
var uid = cljs.core.get_in.cljs$core$IFn$_invoke$arity$2(db,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"auth","auth",1389754926),new cljs.core.Keyword(null,"uid","uid",-1447769400)], null));
return cljs.core.update_in.cljs$core$IFn$_invoke$arity$4(db,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"users","users",-713552705),uid,new cljs.core.Keyword(null,"profile","profile",-545963874)], null),cljs.core.merge,cljs.core.select_keys(profile,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"first-name","first-name",-1559982131),new cljs.core.Keyword(null,"last-name","last-name",-1695738974)], null)));
}));
re_frame.core.reg_event_fx.cljs$core$IFn$_invoke$arity$3(new cljs.core.Keyword(null,"delete-account","delete-account",725157341),app.auth.events.remove_user_interceptors,(function (p__25960,_){
var map__25961 = p__25960;
var map__25961__$1 = (((((!((map__25961 == null))))?(((((map__25961.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__25961.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,map__25961):map__25961);
var db = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__25961__$1,new cljs.core.Keyword(null,"db","db",993250759));
var uid = cljs.core.get_in.cljs$core$IFn$_invoke$arity$2(db,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"auth","auth",1389754926),new cljs.core.Keyword(null,"uid","uid",-1447769400)], null));
return new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"db","db",993250759),cljs.core.update_in.cljs$core$IFn$_invoke$arity$4(cljs.core.assoc_in(db,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"auth","auth",1389754926),new cljs.core.Keyword(null,"uid","uid",-1447769400)], null),null),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"users","users",-713552705)], null),cljs.core.dissoc,uid),new cljs.core.Keyword(null,"dispatch","dispatch",1319337009),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"set-active-page","set-active-page",440775698),new cljs.core.Keyword(null,"recipes","recipes",-325236209)], null),new cljs.core.Keyword(null,"navigate-to","navigate-to",-1161651312),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"path","path",-188191168),"/recipes"], null)], null);
}));

//# sourceMappingURL=app.auth.events.js.map