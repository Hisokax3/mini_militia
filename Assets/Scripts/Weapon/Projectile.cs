using UnityEngine;

public class Projectile : MonoBehaviour
{
    public float speed = 10f;
    private Rigidbody2D rb;

    void Awake()
    {
        rb = GetComponent<Rigidbody2D>();
    }

    void Start()
    {
        Destroy(gameObject, 3f);
    }

    public void AddInitialVelocity(Vector2 addedVelocity)
    {
        Vector2 baseVelocity = transform.right * speed;
        rb.linearVelocity = baseVelocity + addedVelocity;
    }

    void OnTriggerEnter2D(Collider2D other)
    {
        Destroy(gameObject);
    }
}
