PGDMP                         w           PhoneCommerce    11.2    11.2 J    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �           1262    17012    PhoneCommerce    DATABASE     m   CREATE DATABASE "PhoneCommerce" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';
    DROP DATABASE "PhoneCommerce";
             postgres    false            �            1259    17013    pk_autoincrement    SEQUENCE     y   CREATE SEQUENCE public.pk_autoincrement
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.pk_autoincrement;
       public       postgres    false            �            1259    17015    address    TABLE     w  CREATE TABLE public.address (
    namelastname character varying NOT NULL,
    address character varying NOT NULL,
    city character varying NOT NULL,
    province character varying NOT NULL,
    zipcode character varying NOT NULL,
    tel character varying NOT NULL,
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    users bigint NOT NULL
);
    DROP TABLE public.address;
       public         postgres    false    196            �            1259    17022    administrator    TABLE     �   CREATE TABLE public.administrator (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    email character varying NOT NULL,
    password character varying
);
 !   DROP TABLE public.administrator;
       public         postgres    false    196            �            1259    17029    contiene    TABLE     �   CREATE TABLE public.contiene (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    wishlist bigint,
    product bigint
);
    DROP TABLE public.contiene;
       public         postgres    false    196            �            1259    17033    include    TABLE     �   CREATE TABLE public.include (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    product bigint NOT NULL,
    orders bigint NOT NULL,
    quantity integer
);
    DROP TABLE public.include;
       public         postgres    false    196            �            1259    17037 	   inserisce    TABLE     �   CREATE TABLE public.inserisce (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    admin bigint NOT NULL,
    product bigint NOT NULL
);
    DROP TABLE public.inserisce;
       public         postgres    false    196            �            1259    17041    orders    TABLE     �   CREATE TABLE public.orders (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    date date NOT NULL,
    total numeric,
    users bigint NOT NULL,
    address bigint
);
    DROP TABLE public.orders;
       public         postgres    false    196            �            1259    17048    product    TABLE       CREATE TABLE public.product (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    name character varying NOT NULL,
    description character varying,
    price character varying NOT NULL,
    category bigint NOT NULL,
    visible boolean DEFAULT true
);
    DROP TABLE public.product;
       public         postgres    false    196            �            1259    17055    productcategory    TABLE     �   CREATE TABLE public.productcategory (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    name character varying NOT NULL,
    subcategory bigint,
    visible boolean DEFAULT true
);
 #   DROP TABLE public.productcategory;
       public         postgres    false    196            �            1259    17062    review    TABLE       CREATE TABLE public.review (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    title character varying NOT NULL,
    text character varying NOT NULL,
    feedback integer,
    users bigint NOT NULL,
    product bigint NOT NULL
);
    DROP TABLE public.review;
       public         postgres    false    196            �            1259    17069    users    TABLE     Z  CREATE TABLE public.users (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    name character varying NOT NULL,
    surname character varying NOT NULL,
    username character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL,
    admin boolean DEFAULT false NOT NULL
);
    DROP TABLE public.users;
       public         postgres    false    196            �            1259    17077    wishlist    TABLE     �   CREATE TABLE public.wishlist (
    id bigint DEFAULT nextval('public.pk_autoincrement'::regclass) NOT NULL,
    title character varying NOT NULL,
    users bigint NOT NULL,
    type character varying NOT NULL
);
    DROP TABLE public.wishlist;
       public         postgres    false    196            �          0    17015    address 
   TABLE DATA               a   COPY public.address (namelastname, address, city, province, zipcode, tel, id, users) FROM stdin;
    public       postgres    false    197   dY       �          0    17022    administrator 
   TABLE DATA               <   COPY public.administrator (id, email, password) FROM stdin;
    public       postgres    false    198   
Z       �          0    17029    contiene 
   TABLE DATA               9   COPY public.contiene (id, wishlist, product) FROM stdin;
    public       postgres    false    199   9Z       �          0    17033    include 
   TABLE DATA               @   COPY public.include (id, product, orders, quantity) FROM stdin;
    public       postgres    false    200   Z       �          0    17037 	   inserisce 
   TABLE DATA               7   COPY public.inserisce (id, admin, product) FROM stdin;
    public       postgres    false    201   �Z       �          0    17041    orders 
   TABLE DATA               A   COPY public.orders (id, date, total, users, address) FROM stdin;
    public       postgres    false    202   �Z       �          0    17048    product 
   TABLE DATA               R   COPY public.product (id, name, description, price, category, visible) FROM stdin;
    public       postgres    false    203   d[       �          0    17055    productcategory 
   TABLE DATA               I   COPY public.productcategory (id, name, subcategory, visible) FROM stdin;
    public       postgres    false    204   9\       �          0    17062    review 
   TABLE DATA               K   COPY public.review (id, title, text, feedback, users, product) FROM stdin;
    public       postgres    false    205   {\       �          0    17069    users 
   TABLE DATA               T   COPY public.users (id, name, surname, username, email, password, admin) FROM stdin;
    public       postgres    false    206   �\       �          0    17077    wishlist 
   TABLE DATA               :   COPY public.wishlist (id, title, users, type) FROM stdin;
    public       postgres    false    207   6]       �           0    0    pk_autoincrement    SEQUENCE SET     @   SELECT pg_catalog.setval('public.pk_autoincrement', 261, true);
            public       postgres    false    196            �           2606    17085     administrator Administrator_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.administrator
    ADD CONSTRAINT "Administrator_pkey" PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.administrator DROP CONSTRAINT "Administrator_pkey";
       public         postgres    false    198                       2606    17087    contiene CONTIENE_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.contiene
    ADD CONSTRAINT "CONTIENE_pkey" PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.contiene DROP CONSTRAINT "CONTIENE_pkey";
       public         postgres    false    199                       2606    17089    inserisce Contiene_PK_UNIQUE 
   CONSTRAINT     W   ALTER TABLE ONLY public.inserisce
    ADD CONSTRAINT "Contiene_PK_UNIQUE" UNIQUE (id);
 H   ALTER TABLE ONLY public.inserisce DROP CONSTRAINT "Contiene_PK_UNIQUE";
       public         postgres    false    201                       2606    17091    include INCLUDE_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.include
    ADD CONSTRAINT "INCLUDE_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.include DROP CONSTRAINT "INCLUDE_pkey";
       public         postgres    false    200                       2606    17093    inserisce INSERISCE_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.inserisce
    ADD CONSTRAINT "INSERISCE_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.inserisce DROP CONSTRAINT "INSERISCE_pkey";
       public         postgres    false    201                       2606    17095    orders Order_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT "Order_pkey" PRIMARY KEY (id);
 =   ALTER TABLE ONLY public.orders DROP CONSTRAINT "Order_pkey";
       public         postgres    false    202                       2606    17097    include PK_Unique_Include 
   CONSTRAINT     T   ALTER TABLE ONLY public.include
    ADD CONSTRAINT "PK_Unique_Include" UNIQUE (id);
 E   ALTER TABLE ONLY public.include DROP CONSTRAINT "PK_Unique_Include";
       public         postgres    false    200                       2606    17099    contiene PK_unique 
   CONSTRAINT     M   ALTER TABLE ONLY public.contiene
    ADD CONSTRAINT "PK_unique" UNIQUE (id);
 >   ALTER TABLE ONLY public.contiene DROP CONSTRAINT "PK_unique";
       public         postgres    false    199                        2606    17101 %   administrator PK_unique_Administrator 
   CONSTRAINT     `   ALTER TABLE ONLY public.administrator
    ADD CONSTRAINT "PK_unique_Administrator" UNIQUE (id);
 Q   ALTER TABLE ONLY public.administrator DROP CONSTRAINT "PK_unique_Administrator";
       public         postgres    false    198                       2606    17103    orders PK_unique_Order 
   CONSTRAINT     Q   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT "PK_unique_Order" UNIQUE (id);
 B   ALTER TABLE ONLY public.orders DROP CONSTRAINT "PK_unique_Order";
       public         postgres    false    202                       2606    17105    product PK_unique_Product 
   CONSTRAINT     T   ALTER TABLE ONLY public.product
    ADD CONSTRAINT "PK_unique_Product" UNIQUE (id);
 E   ALTER TABLE ONLY public.product DROP CONSTRAINT "PK_unique_Product";
       public         postgres    false    203                        2606    17107 )   productcategory PK_unique_ProductCategory 
   CONSTRAINT     d   ALTER TABLE ONLY public.productcategory
    ADD CONSTRAINT "PK_unique_ProductCategory" UNIQUE (id);
 U   ALTER TABLE ONLY public.productcategory DROP CONSTRAINT "PK_unique_ProductCategory";
       public         postgres    false    204            $           2606    17109    review PK_unique_Review 
   CONSTRAINT     R   ALTER TABLE ONLY public.review
    ADD CONSTRAINT "PK_unique_Review" UNIQUE (id);
 C   ALTER TABLE ONLY public.review DROP CONSTRAINT "PK_unique_Review";
       public         postgres    false    205            (           2606    17111    users PK_unique_User 
   CONSTRAINT     O   ALTER TABLE ONLY public.users
    ADD CONSTRAINT "PK_unique_User" UNIQUE (id);
 @   ALTER TABLE ONLY public.users DROP CONSTRAINT "PK_unique_User";
       public         postgres    false    206            0           2606    17113    wishlist PK_unique_Wishlist 
   CONSTRAINT     V   ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT "PK_unique_Wishlist" UNIQUE (id);
 G   ALTER TABLE ONLY public.wishlist DROP CONSTRAINT "PK_unique_Wishlist";
       public         postgres    false    207            "           2606    17115 $   productcategory ProductCategory_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.productcategory
    ADD CONSTRAINT "ProductCategory_pkey" PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.productcategory DROP CONSTRAINT "ProductCategory_pkey";
       public         postgres    false    204                       2606    17117    product Product_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.product
    ADD CONSTRAINT "Product_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.product DROP CONSTRAINT "Product_pkey";
       public         postgres    false    203            &           2606    17119    review Review_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.review
    ADD CONSTRAINT "Review_pkey" PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.review DROP CONSTRAINT "Review_pkey";
       public         postgres    false    205            *           2606    17121    users User_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY public.users
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);
 ;   ALTER TABLE ONLY public.users DROP CONSTRAINT "User_pkey";
       public         postgres    false    206            2           2606    17123    wishlist Wishlist_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT "Wishlist_pkey" PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.wishlist DROP CONSTRAINT "Wishlist_pkey";
       public         postgres    false    207                       2606    17125 +   contiene contiene_unique_WishlistAndProduct 
   CONSTRAINT     u   ALTER TABLE ONLY public.contiene
    ADD CONSTRAINT "contiene_unique_WishlistAndProduct" UNIQUE (wishlist, product);
 W   ALTER TABLE ONLY public.contiene DROP CONSTRAINT "contiene_unique_WishlistAndProduct";
       public         postgres    false    199    199            �           2606    17127    address pk_indirizzo 
   CONSTRAINT     R   ALTER TABLE ONLY public.address
    ADD CONSTRAINT pk_indirizzo PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.address DROP CONSTRAINT pk_indirizzo;
       public         postgres    false    197            �           2606    17129    address pk_unique_indirizzo 
   CONSTRAINT     T   ALTER TABLE ONLY public.address
    ADD CONSTRAINT pk_unique_indirizzo UNIQUE (id);
 E   ALTER TABLE ONLY public.address DROP CONSTRAINT pk_unique_indirizzo;
       public         postgres    false    197                       2606    17131 (   administrator unique_administrator_email 
   CONSTRAINT     d   ALTER TABLE ONLY public.administrator
    ADD CONSTRAINT unique_administrator_email UNIQUE (email);
 R   ALTER TABLE ONLY public.administrator DROP CONSTRAINT unique_administrator_email;
       public         postgres    false    198            ,           2606    17133    users unique_user_email 
   CONSTRAINT     S   ALTER TABLE ONLY public.users
    ADD CONSTRAINT unique_user_email UNIQUE (email);
 A   ALTER TABLE ONLY public.users DROP CONSTRAINT unique_user_email;
       public         postgres    false    206            .           2606    17135    users unique_user_username 
   CONSTRAINT     Y   ALTER TABLE ONLY public.users
    ADD CONSTRAINT unique_user_username UNIQUE (username);
 D   ALTER TABLE ONLY public.users DROP CONSTRAINT unique_user_username;
       public         postgres    false    206                       1259    17136    fki_Contiene_FK_Administrator    INDEX     V   CREATE INDEX "fki_Contiene_FK_Administrator" ON public.inserisce USING btree (admin);
 3   DROP INDEX public."fki_Contiene_FK_Administrator";
       public         postgres    false    201                       1259    17137    fki_Contiene_FK_Product    INDEX     R   CREATE INDEX "fki_Contiene_FK_Product" ON public.inserisce USING btree (product);
 -   DROP INDEX public."fki_Contiene_FK_Product";
       public         postgres    false    201            	           1259    17138    fki_Contiene_FK_product    INDEX     Q   CREATE INDEX "fki_Contiene_FK_product" ON public.contiene USING btree (product);
 -   DROP INDEX public."fki_Contiene_FK_product";
       public         postgres    false    199            
           1259    17139    fki_Contiene_FK_wishlist    INDEX     S   CREATE INDEX "fki_Contiene_FK_wishlist" ON public.contiene USING btree (wishlist);
 .   DROP INDEX public."fki_Contiene_FK_wishlist";
       public         postgres    false    199                       1259    17140    fki_FK_Include_Order    INDEX     L   CREATE INDEX "fki_FK_Include_Order" ON public.include USING btree (orders);
 *   DROP INDEX public."fki_FK_Include_Order";
       public         postgres    false    200                       1259    17141    fki_FK_Include_Product    INDEX     O   CREATE INDEX "fki_FK_Include_Product" ON public.include USING btree (product);
 ,   DROP INDEX public."fki_FK_Include_Product";
       public         postgres    false    200            �           1259    17142    fki_fk_indirizzo_users    INDEX     K   CREATE INDEX fki_fk_indirizzo_users ON public.address USING btree (users);
 *   DROP INDEX public.fki_fk_indirizzo_users;
       public         postgres    false    197            8           2606    17143 #   inserisce Contiene_FK_Administrator    FK CONSTRAINT     �   ALTER TABLE ONLY public.inserisce
    ADD CONSTRAINT "Contiene_FK_Administrator" FOREIGN KEY (admin) REFERENCES public.administrator(id);
 O   ALTER TABLE ONLY public.inserisce DROP CONSTRAINT "Contiene_FK_Administrator";
       public       postgres    false    201    198    3070            9           2606    17148    inserisce Contiene_FK_Product    FK CONSTRAINT     �   ALTER TABLE ONLY public.inserisce
    ADD CONSTRAINT "Contiene_FK_Product" FOREIGN KEY (product) REFERENCES public.product(id);
 I   ALTER TABLE ONLY public.inserisce DROP CONSTRAINT "Contiene_FK_Product";
       public       postgres    false    203    3100    201            4           2606    17153    contiene Contiene_FK_product    FK CONSTRAINT        ALTER TABLE ONLY public.contiene
    ADD CONSTRAINT "Contiene_FK_product" FOREIGN KEY (product) REFERENCES public.product(id);
 H   ALTER TABLE ONLY public.contiene DROP CONSTRAINT "Contiene_FK_product";
       public       postgres    false    199    203    3100            5           2606    17158    contiene Contiene_FK_wishlist    FK CONSTRAINT     �   ALTER TABLE ONLY public.contiene
    ADD CONSTRAINT "Contiene_FK_wishlist" FOREIGN KEY (wishlist) REFERENCES public.wishlist(id);
 I   ALTER TABLE ONLY public.contiene DROP CONSTRAINT "Contiene_FK_wishlist";
       public       postgres    false    199    207    3120            6           2606    17163    include FK_Include_Order    FK CONSTRAINT     y   ALTER TABLE ONLY public.include
    ADD CONSTRAINT "FK_Include_Order" FOREIGN KEY (orders) REFERENCES public.orders(id);
 D   ALTER TABLE ONLY public.include DROP CONSTRAINT "FK_Include_Order";
       public       postgres    false    3096    200    202            7           2606    17168    include FK_Include_Product    FK CONSTRAINT     }   ALTER TABLE ONLY public.include
    ADD CONSTRAINT "FK_Include_Product" FOREIGN KEY (product) REFERENCES public.product(id);
 F   ALTER TABLE ONLY public.include DROP CONSTRAINT "FK_Include_Product";
       public       postgres    false    3100    200    203            ;           2606    17173 #   product FK_ProductCategory_category    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT "FK_ProductCategory_category" FOREIGN KEY (category) REFERENCES public.productcategory(id);
 O   ALTER TABLE ONLY public.product DROP CONSTRAINT "FK_ProductCategory_category";
       public       postgres    false    204    203    3104            <           2606    17178 .   productcategory FK_ProductCategory_subcategory    FK CONSTRAINT     �   ALTER TABLE ONLY public.productcategory
    ADD CONSTRAINT "FK_ProductCategory_subcategory" FOREIGN KEY (subcategory) REFERENCES public.productcategory(id);
 Z   ALTER TABLE ONLY public.productcategory DROP CONSTRAINT "FK_ProductCategory_subcategory";
       public       postgres    false    204    204    3104            =           2606    17183    review FK_Review_product    FK CONSTRAINT     {   ALTER TABLE ONLY public.review
    ADD CONSTRAINT "FK_Review_product" FOREIGN KEY (product) REFERENCES public.product(id);
 D   ALTER TABLE ONLY public.review DROP CONSTRAINT "FK_Review_product";
       public       postgres    false    205    203    3100            >           2606    17188    review FK_Review_user    FK CONSTRAINT     t   ALTER TABLE ONLY public.review
    ADD CONSTRAINT "FK_Review_user" FOREIGN KEY (users) REFERENCES public.users(id);
 A   ALTER TABLE ONLY public.review DROP CONSTRAINT "FK_Review_user";
       public       postgres    false    3112    206    205            ?           2606    17193    wishlist FK_Wishlist_user    FK CONSTRAINT     x   ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT "FK_Wishlist_user" FOREIGN KEY (users) REFERENCES public.users(id);
 E   ALTER TABLE ONLY public.wishlist DROP CONSTRAINT "FK_Wishlist_user";
       public       postgres    false    207    3112    206            :           2606    17198    orders FK_user    FK CONSTRAINT     m   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT "FK_user" FOREIGN KEY (users) REFERENCES public.users(id);
 :   ALTER TABLE ONLY public.orders DROP CONSTRAINT "FK_user";
       public       postgres    false    3112    206    202            3           2606    17203    address fk_indirizzo_users    FK CONSTRAINT     w   ALTER TABLE ONLY public.address
    ADD CONSTRAINT fk_indirizzo_users FOREIGN KEY (users) REFERENCES public.users(id);
 D   ALTER TABLE ONLY public.address DROP CONSTRAINT fk_indirizzo_users;
       public       postgres    false    197    3112    206            �   �   x���1
1E��)r �d&k���l'A�l�$�ݑ�"x{�W������?JJ�/\s��V ɲ5~�~5��vp.Rde�Gp����:XB�Du�9/����ٸkn�����wny��N���$��Ӆ�8D4��-ر'����O�WJ},�>�      �      x�3�LL���s �z�%��F�&\1z\\\ s�      �   6   x�3�4�4���443�2�4�-��L����	�in Vadb
f[q��qqq ?�	�      �   P   x�=���0���*�K���$Ty�8نf�1A#Th̓@�` � ;�0����,͖���.��UxGa[U����{���<      �      x������ � �      �   X   x�U���0Cѳ��6K/鿎p�����KP���5���DI!�w��,�-K[��Q�n�6]�K��8�&~r_D� >!%      �   �   x�=��n�0D���@&6u8p㈄��z��b��Bdѯ���j3of��Cϑ��St3�:�P����I?{|jS����j���{
�1��	���Xh[��Fϴ��i�r���C�`�Jm�nw���l7K� S��X脮+�ch����_W�/�~Z���j�t<FN���ȃoy�ע�De������B<_H      �   2   x�34�t,(�I���,�22���L��̈́�M�9�KJA�4�=... LS      �      x������ � �      �   �   x�e���0D���D4�-b�b��X$�Ԥ�z`��O���:cp��Ò�0n�EO�Ds�D���F�a
���7��9u���7Ug,�
�L�1�L:QF�kq�4�\���i��N�1�:�Kj{U{��z��@�      �   w   x�m�M
� F�z
O�T�:BI�%H���\��=�{LR�C���� �{��"f��)����6���F���ă&��%�L!�>��G�[Ek��hE�Ge�cj����0�/��W�     