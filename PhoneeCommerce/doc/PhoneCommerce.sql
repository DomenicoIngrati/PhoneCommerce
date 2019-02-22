PGDMP                          w            PhoneCommerce    11.2    11.2 I    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �           1262    16452    PhoneCommerce    DATABASE     m   CREATE DATABASE "PhoneCommerce" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';
    DROP DATABASE "PhoneCommerce";
             postgres    false            �            1259    16765    pk_autoincrement    SEQUENCE     y   CREATE SEQUENCE public.pk_autoincrement
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.pk_autoincrement;
       public       postgres    false            �            1259    16490    administrator    TABLE     �   CREATE TABLE public.administrator (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    email character varying NOT NULL,
    password character varying
);
 !   DROP TABLE public.administrator;
       public         postgres    false    205            �            1259    16506    contiene    TABLE     �   CREATE TABLE public.contiene (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    wishlist bigint,
    product bigint
);
    DROP TABLE public.contiene;
       public         postgres    false    205            �            1259    16776    include    TABLE     �   CREATE TABLE public.include (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    product bigint NOT NULL,
    "order" bigint NOT NULL
);
    DROP TABLE public.include;
       public         postgres    false    205            �            1259    16539 	   inserisce    TABLE     �   CREATE TABLE public.inserisce (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    admin bigint NOT NULL,
    product bigint NOT NULL
);
    DROP TABLE public.inserisce;
       public         postgres    false    205            �            1259    16477    order    TABLE     �   CREATE TABLE public."order" (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    date date NOT NULL,
    total money,
    "user" bigint NOT NULL
);
    DROP TABLE public."order";
       public         postgres    false    205            �            1259    16461    product    TABLE     �   CREATE TABLE public.product (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    name character varying NOT NULL,
    description character varying,
    price character varying NOT NULL,
    category bigint NOT NULL
);
    DROP TABLE public.product;
       public         postgres    false    205            �            1259    16498    productcategory    TABLE     �   CREATE TABLE public.productcategory (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    name character varying NOT NULL,
    subcategory bigint
);
 #   DROP TABLE public.productcategory;
       public         postgres    false    205            �            1259    16482    review    TABLE       CREATE TABLE public.review (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    title character varying NOT NULL,
    text character varying NOT NULL,
    feedback integer,
    "user" bigint NOT NULL,
    product bigint NOT NULL
);
    DROP TABLE public.review;
       public         postgres    false    205            �            1259    16453    user    TABLE     1  CREATE TABLE public."user" (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    name character varying NOT NULL,
    surname character varying NOT NULL,
    username character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL
);
    DROP TABLE public."user";
       public         postgres    false    205            �            1259    16469    wishlist    TABLE     �   CREATE TABLE public.wishlist (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    title character varying NOT NULL,
    "user" bigint NOT NULL
);
    DROP TABLE public.wishlist;
       public         postgres    false    205            �          0    16490    administrator 
   TABLE DATA               <   COPY public.administrator (id, email, password) FROM stdin;
    public       postgres    false    201   *W       �          0    16506    contiene 
   TABLE DATA               9   COPY public.contiene (id, wishlist, product) FROM stdin;
    public       postgres    false    203   YW       �          0    16776    include 
   TABLE DATA               7   COPY public.include (id, product, "order") FROM stdin;
    public       postgres    false    206   vW       �          0    16539 	   inserisce 
   TABLE DATA               7   COPY public.inserisce (id, admin, product) FROM stdin;
    public       postgres    false    204   �W       �          0    16477    order 
   TABLE DATA               :   COPY public."order" (id, date, total, "user") FROM stdin;
    public       postgres    false    199   �W       �          0    16461    product 
   TABLE DATA               I   COPY public.product (id, name, description, price, category) FROM stdin;
    public       postgres    false    197   �W       �          0    16498    productcategory 
   TABLE DATA               @   COPY public.productcategory (id, name, subcategory) FROM stdin;
    public       postgres    false    202   �W       �          0    16482    review 
   TABLE DATA               L   COPY public.review (id, title, text, feedback, "user", product) FROM stdin;
    public       postgres    false    200   X       �          0    16453    user 
   TABLE DATA               N   COPY public."user" (id, name, surname, username, email, password) FROM stdin;
    public       postgres    false    196   $X       �          0    16469    wishlist 
   TABLE DATA               5   COPY public.wishlist (id, title, "user") FROM stdin;
    public       postgres    false    198   AX       �           0    0    pk_autoincrement    SEQUENCE SET     >   SELECT pg_catalog.setval('public.pk_autoincrement', 4, true);
            public       postgres    false    205                       2606    16497     administrator Administrator_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.administrator
    ADD CONSTRAINT "Administrator_pkey" PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.administrator DROP CONSTRAINT "Administrator_pkey";
       public         postgres    false    201                       2606    16510    contiene CONTIENE_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.contiene
    ADD CONSTRAINT "CONTIENE_pkey" PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.contiene DROP CONSTRAINT "CONTIENE_pkey";
       public         postgres    false    203            #           2606    16545    inserisce Contiene_PK_UNIQUE 
   CONSTRAINT     W   ALTER TABLE ONLY public.inserisce
    ADD CONSTRAINT "Contiene_PK_UNIQUE" UNIQUE (id);
 H   ALTER TABLE ONLY public.inserisce DROP CONSTRAINT "Contiene_PK_UNIQUE";
       public         postgres    false    204                       2606    16604 5   productcategory FK_unique_ProductCategory_subcategory 
   CONSTRAINT     y   ALTER TABLE ONLY public.productcategory
    ADD CONSTRAINT "FK_unique_ProductCategory_subcategory" UNIQUE (subcategory);
 a   ALTER TABLE ONLY public.productcategory DROP CONSTRAINT "FK_unique_ProductCategory_subcategory";
       public         postgres    false    202            �           2606    16611 "   product FK_unique_Product_category 
   CONSTRAINT     c   ALTER TABLE ONLY public.product
    ADD CONSTRAINT "FK_unique_Product_category" UNIQUE (category);
 N   ALTER TABLE ONLY public.product DROP CONSTRAINT "FK_unique_Product_category";
       public         postgres    false    197            	           2606    16597    review FK_unique_Review_product 
   CONSTRAINT     _   ALTER TABLE ONLY public.review
    ADD CONSTRAINT "FK_unique_Review_product" UNIQUE (product);
 K   ALTER TABLE ONLY public.review DROP CONSTRAINT "FK_unique_Review_product";
       public         postgres    false    200                       2606    16590    review FK_unique_Review_user 
   CONSTRAINT     [   ALTER TABLE ONLY public.review
    ADD CONSTRAINT "FK_unique_Review_user" UNIQUE ("user");
 H   ALTER TABLE ONLY public.review DROP CONSTRAINT "FK_unique_Review_user";
       public         postgres    false    200                       2606    16576    order FK_unique_User 
   CONSTRAINT     U   ALTER TABLE ONLY public."order"
    ADD CONSTRAINT "FK_unique_User" UNIQUE ("user");
 B   ALTER TABLE ONLY public."order" DROP CONSTRAINT "FK_unique_User";
       public         postgres    false    199            �           2606    16583     wishlist FK_unique_Wishlist_user 
   CONSTRAINT     _   ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT "FK_unique_Wishlist_user" UNIQUE ("user");
 L   ALTER TABLE ONLY public.wishlist DROP CONSTRAINT "FK_unique_Wishlist_user";
       public         postgres    false    198            )           2606    16780    include INCLUDE_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.include
    ADD CONSTRAINT "INCLUDE_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.include DROP CONSTRAINT "INCLUDE_pkey";
       public         postgres    false    206            %           2606    16543    inserisce INSERISCE_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.inserisce
    ADD CONSTRAINT "INSERISCE_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.inserisce DROP CONSTRAINT "INSERISCE_pkey";
       public         postgres    false    204                       2606    16481    order Order_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."order"
    ADD CONSTRAINT "Order_pkey" PRIMARY KEY (id);
 >   ALTER TABLE ONLY public."order" DROP CONSTRAINT "Order_pkey";
       public         postgres    false    199            +           2606    16782    include PK_Unique_Include 
   CONSTRAINT     T   ALTER TABLE ONLY public.include
    ADD CONSTRAINT "PK_Unique_Include" UNIQUE (id);
 E   ALTER TABLE ONLY public.include DROP CONSTRAINT "PK_Unique_Include";
       public         postgres    false    206                       2606    16512    contiene PK_unique 
   CONSTRAINT     M   ALTER TABLE ONLY public.contiene
    ADD CONSTRAINT "PK_unique" UNIQUE (id);
 >   ALTER TABLE ONLY public.contiene DROP CONSTRAINT "PK_unique";
       public         postgres    false    203                       2606    16514 %   administrator PK_unique_Administrator 
   CONSTRAINT     `   ALTER TABLE ONLY public.administrator
    ADD CONSTRAINT "PK_unique_Administrator" UNIQUE (id);
 Q   ALTER TABLE ONLY public.administrator DROP CONSTRAINT "PK_unique_Administrator";
       public         postgres    false    201                       2606    16516    order PK_unique_Order 
   CONSTRAINT     R   ALTER TABLE ONLY public."order"
    ADD CONSTRAINT "PK_unique_Order" UNIQUE (id);
 C   ALTER TABLE ONLY public."order" DROP CONSTRAINT "PK_unique_Order";
       public         postgres    false    199            �           2606    16518    product PK_unique_Product 
   CONSTRAINT     T   ALTER TABLE ONLY public.product
    ADD CONSTRAINT "PK_unique_Product" UNIQUE (id);
 E   ALTER TABLE ONLY public.product DROP CONSTRAINT "PK_unique_Product";
       public         postgres    false    197                       2606    16520 )   productcategory PK_unique_ProductCategory 
   CONSTRAINT     d   ALTER TABLE ONLY public.productcategory
    ADD CONSTRAINT "PK_unique_ProductCategory" UNIQUE (id);
 U   ALTER TABLE ONLY public.productcategory DROP CONSTRAINT "PK_unique_ProductCategory";
       public         postgres    false    202                       2606    16522    review PK_unique_Review 
   CONSTRAINT     R   ALTER TABLE ONLY public.review
    ADD CONSTRAINT "PK_unique_Review" UNIQUE (id);
 C   ALTER TABLE ONLY public.review DROP CONSTRAINT "PK_unique_Review";
       public         postgres    false    200            �           2606    16524    user PK_unique_User 
   CONSTRAINT     P   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "PK_unique_User" UNIQUE (id);
 A   ALTER TABLE ONLY public."user" DROP CONSTRAINT "PK_unique_User";
       public         postgres    false    196            �           2606    16526    wishlist PK_unique_Wishlist 
   CONSTRAINT     V   ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT "PK_unique_Wishlist" UNIQUE (id);
 G   ALTER TABLE ONLY public.wishlist DROP CONSTRAINT "PK_unique_Wishlist";
       public         postgres    false    198                       2606    16505 $   productcategory ProductCategory_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.productcategory
    ADD CONSTRAINT "ProductCategory_pkey" PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.productcategory DROP CONSTRAINT "ProductCategory_pkey";
       public         postgres    false    202            �           2606    16468    product Product_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.product
    ADD CONSTRAINT "Product_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.product DROP CONSTRAINT "Product_pkey";
       public         postgres    false    197                       2606    16489    review Review_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.review
    ADD CONSTRAINT "Review_pkey" PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.review DROP CONSTRAINT "Review_pkey";
       public         postgres    false    200            �           2606    16460    user User_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);
 <   ALTER TABLE ONLY public."user" DROP CONSTRAINT "User_pkey";
       public         postgres    false    196                       2606    16528    wishlist Wishlist_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT "Wishlist_pkey" PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.wishlist DROP CONSTRAINT "Wishlist_pkey";
       public         postgres    false    198                       2606    16799 (   administrator unique_administrator_email 
   CONSTRAINT     d   ALTER TABLE ONLY public.administrator
    ADD CONSTRAINT unique_administrator_email UNIQUE (email);
 R   ALTER TABLE ONLY public.administrator DROP CONSTRAINT unique_administrator_email;
       public         postgres    false    201            �           2606    16801    user unique_user_email 
   CONSTRAINT     T   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT unique_user_email UNIQUE (email);
 B   ALTER TABLE ONLY public."user" DROP CONSTRAINT unique_user_email;
       public         postgres    false    196            �           2606    16803    user unique_user_username 
   CONSTRAINT     Z   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT unique_user_username UNIQUE (username);
 E   ALTER TABLE ONLY public."user" DROP CONSTRAINT unique_user_username;
       public         postgres    false    196            &           1259    16562    fki_Contiene_FK_Administrator    INDEX     V   CREATE INDEX "fki_Contiene_FK_Administrator" ON public.inserisce USING btree (admin);
 3   DROP INDEX public."fki_Contiene_FK_Administrator";
       public         postgres    false    204            '           1259    16556    fki_Contiene_FK_Product    INDEX     R   CREATE INDEX "fki_Contiene_FK_Product" ON public.inserisce USING btree (product);
 -   DROP INDEX public."fki_Contiene_FK_Product";
       public         postgres    false    204                        1259    16574    fki_Contiene_FK_product    INDEX     Q   CREATE INDEX "fki_Contiene_FK_product" ON public.contiene USING btree (product);
 -   DROP INDEX public."fki_Contiene_FK_product";
       public         postgres    false    203            !           1259    16568    fki_Contiene_FK_wishlist    INDEX     S   CREATE INDEX "fki_Contiene_FK_wishlist" ON public.contiene USING btree (wishlist);
 .   DROP INDEX public."fki_Contiene_FK_wishlist";
       public         postgres    false    203            ,           1259    16794    fki_FK_Include_Order    INDEX     M   CREATE INDEX "fki_FK_Include_Order" ON public.include USING btree ("order");
 *   DROP INDEX public."fki_FK_Include_Order";
       public         postgres    false    206            -           1259    16788    fki_FK_Include_Product    INDEX     O   CREATE INDEX "fki_FK_Include_Product" ON public.include USING btree (product);
 ,   DROP INDEX public."fki_FK_Include_Product";
       public         postgres    false    206            7           2606    16557 #   inserisce Contiene_FK_Administrator    FK CONSTRAINT     �   ALTER TABLE ONLY public.inserisce
    ADD CONSTRAINT "Contiene_FK_Administrator" FOREIGN KEY (admin) REFERENCES public.administrator(id);
 O   ALTER TABLE ONLY public.inserisce DROP CONSTRAINT "Contiene_FK_Administrator";
       public       postgres    false    201    204    3089            6           2606    16551    inserisce Contiene_FK_Product    FK CONSTRAINT     �   ALTER TABLE ONLY public.inserisce
    ADD CONSTRAINT "Contiene_FK_Product" FOREIGN KEY (product) REFERENCES public.product(id);
 I   ALTER TABLE ONLY public.inserisce DROP CONSTRAINT "Contiene_FK_Product";
       public       postgres    false    3067    204    197            5           2606    16569    contiene Contiene_FK_product    FK CONSTRAINT        ALTER TABLE ONLY public.contiene
    ADD CONSTRAINT "Contiene_FK_product" FOREIGN KEY (product) REFERENCES public.product(id);
 H   ALTER TABLE ONLY public.contiene DROP CONSTRAINT "Contiene_FK_product";
       public       postgres    false    3067    203    197            4           2606    16563    contiene Contiene_FK_wishlist    FK CONSTRAINT     �   ALTER TABLE ONLY public.contiene
    ADD CONSTRAINT "Contiene_FK_wishlist" FOREIGN KEY (wishlist) REFERENCES public.wishlist(id);
 I   ALTER TABLE ONLY public.contiene DROP CONSTRAINT "Contiene_FK_wishlist";
       public       postgres    false    198    203    3071            9           2606    16789    include FK_Include_Order    FK CONSTRAINT     {   ALTER TABLE ONLY public.include
    ADD CONSTRAINT "FK_Include_Order" FOREIGN KEY ("order") REFERENCES public."order"(id);
 D   ALTER TABLE ONLY public.include DROP CONSTRAINT "FK_Include_Order";
       public       postgres    false    3077    199    206            8           2606    16783    include FK_Include_Product    FK CONSTRAINT     }   ALTER TABLE ONLY public.include
    ADD CONSTRAINT "FK_Include_Product" FOREIGN KEY (product) REFERENCES public.product(id);
 F   ALTER TABLE ONLY public.include DROP CONSTRAINT "FK_Include_Product";
       public       postgres    false    3067    206    197            .           2606    16612 #   product FK_ProductCategory_category    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT "FK_ProductCategory_category" FOREIGN KEY (category) REFERENCES public.productcategory(id);
 O   ALTER TABLE ONLY public.product DROP CONSTRAINT "FK_ProductCategory_category";
       public       postgres    false    197    202    3099            3           2606    16605 .   productcategory FK_ProductCategory_subcategory    FK CONSTRAINT     �   ALTER TABLE ONLY public.productcategory
    ADD CONSTRAINT "FK_ProductCategory_subcategory" FOREIGN KEY (subcategory) REFERENCES public.productcategory(id);
 Z   ALTER TABLE ONLY public.productcategory DROP CONSTRAINT "FK_ProductCategory_subcategory";
       public       postgres    false    202    3099    202            2           2606    16598    review FK_Review_product    FK CONSTRAINT     {   ALTER TABLE ONLY public.review
    ADD CONSTRAINT "FK_Review_product" FOREIGN KEY (product) REFERENCES public.product(id);
 D   ALTER TABLE ONLY public.review DROP CONSTRAINT "FK_Review_product";
       public       postgres    false    3067    200    197            1           2606    16591    review FK_Review_user    FK CONSTRAINT     v   ALTER TABLE ONLY public.review
    ADD CONSTRAINT "FK_Review_user" FOREIGN KEY ("user") REFERENCES public."user"(id);
 A   ALTER TABLE ONLY public.review DROP CONSTRAINT "FK_Review_user";
       public       postgres    false    196    200    3057            /           2606    16584    wishlist FK_Wishlist_user    FK CONSTRAINT     z   ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT "FK_Wishlist_user" FOREIGN KEY ("user") REFERENCES public."user"(id);
 E   ALTER TABLE ONLY public.wishlist DROP CONSTRAINT "FK_Wishlist_user";
       public       postgres    false    196    198    3057            0           2606    16577    order FK_user    FK CONSTRAINT     p   ALTER TABLE ONLY public."order"
    ADD CONSTRAINT "FK_user" FOREIGN KEY ("user") REFERENCES public."user"(id);
 ;   ALTER TABLE ONLY public."order" DROP CONSTRAINT "FK_user";
       public       postgres    false    196    199    3057            �      x�3�LL���s �z�%��F�&\1z\\\ s�      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     