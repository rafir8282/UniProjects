<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the
 * installation. You don't have to use the web site, you can
 * copy this file to "wp-config.php" and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * MySQL settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://wordpress.org/support/article/editing-wp-config-php/
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'wordpress' );

/** MySQL database username */
define( 'DB_USER', 'root' );

/** MySQL database password */
define( 'DB_PASSWORD', 'root' );

/** MySQL hostname */
define( 'DB_HOST', 'localhost' );

/** Database Charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The Database Collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         '7yaEZ~ITx)7fA?#;AgApks^T}RS0*<8U}gubA}Qm7WV*8a<U)P3XR{zbtsZ<hqY[' );
define( 'SECURE_AUTH_KEY',  'VjfwS^Oh#Vp.DZfr!8%h8%J|x{,w4d`W`}A@5K/rfEH8UzHX(wZDBN!<^-2_WD~Y' );
define( 'LOGGED_IN_KEY',    '-`P<T_3) -j^qSN_DGow#w=JG8 5^LmrI{_/WYVns}%wY5;|F,7SH!_b1st&5EHT' );
define( 'NONCE_KEY',        'e7@S1?0moXg++5*)ssZ=}A*]$CJmy[xw(?,0R/ehj-?n2pGS2uwsociz!AIt.]5~' );
define( 'AUTH_SALT',        'w.*c.nhJb93d+/71IW@_9;Q{MdVE;nB7p:=EHoo+$gSKu%NYn@RvhJ>5e>MW{*/6' );
define( 'SECURE_AUTH_SALT', '?}d?1V`]w^R(U^=c&<jeB`/Uz*-/DrNdrw(Au: ]6mqrsQzU!^ ZR QqxP>6NV%?' );
define( 'LOGGED_IN_SALT',   'bI:}K.uoVzw /mKjC $f-Kd&l.T6;rZPfLEDL.w:bZ$N `QXHqRy]J4LV7m`|]0H' );
define( 'NONCE_SALT',       '$[!*gdpEw%2D.i;%NAWWAMgx/=42!q[JU-84(#]GqxNQO8psHJJ!UY12{{_d< Q!' );

/**#@-*/

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://wordpress.org/support/article/debugging-in-wordpress/
 */
define( 'WP_DEBUG', false );

/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';
