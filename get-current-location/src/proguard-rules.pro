# Add any ProGuard configurations specific to this
# extension here.

-keep public class com.oseamiya.getcurrentlocation.GetCurrentLocation {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'com/oseamiya/getcurrentlocation/repack'
-flattenpackagehierarchy
-dontpreverify
