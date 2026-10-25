using UnityEngine;
using UnityEngine.InputSystem;

public class Weapon : MonoBehaviour
{
    public WeaponData weaponData;
    private Transform firePoint;
    private Rigidbody2D playerRigidbody2D;
    private float nextFireTime = 0f;

    void Awake()
    {
        firePoint = transform.Find("firePoint");
        if (firePoint == null)
        {
            Debug.LogError("No FirePoint found for weapon: " + weaponData.weaponName);
        }
        playerRigidbody2D = GetComponentInParent<Rigidbody2D>();
        if (playerRigidbody2D == null)
        {
            Debug.LogError("No Rigidbody2D found in parent for weapon: " + weaponData.weaponName);
        }
    }

    public void fire()
    {
        if (Time.time >= nextFireTime)
        {
            // Make sure the firePoint was successfully found.
            if (firePoint == null)
            {
                Debug.LogError("FirePoint is null on weapon: " + gameObject.name);
                return;
            }
            GameObject projectileObj =  Instantiate(weaponData.projectilePrefab, firePoint.position, firePoint.rotation);
            Projectile projectileScript = projectileObj.GetComponent<Projectile>();

            if (projectileScript != null && playerRigidbody2D != null)
            {
                projectileScript.AddInitialVelocity(playerRigidbody2D.linearVelocity);
            }
            nextFireTime = Time.time + 1f / weaponData.fireRate;
        }
    }
}
